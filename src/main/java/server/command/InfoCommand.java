package server.command;

import common.ResponseStatusEnum;
import common.ResponseWithTreeSet;
import common.exception.MustBeEmptyException;
import server.manager.CollectionManager;

import java.util.ArrayList;

/**
 * command outputs information about the collection
 *
 * @author Nikita and Vlad
 * @version 0.1
 */
public class InfoCommand extends AbstractCommand {
    private final CollectionManager collectionManager;

    public InfoCommand(CollectionManager collectionManager) {
        super("info", "выводит в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)");
        this.collectionManager = collectionManager;
    }

    @Override
    public ResponseWithTreeSet execute(String argument, Long client) {
        try {
            if (!argument.isEmpty()) throw new MustBeEmptyException();
            ArrayList<String> args = new ArrayList<>();
            args.add(String.valueOf(collectionManager.getLabWork().getClass()));
            args.add(String.valueOf(collectionManager.getLabWork().size()));
            args.add(String.valueOf(collectionManager.getCreatingCollection()));
            return new ResponseWithTreeSet(ResponseStatusEnum.INFO, collectionManager.getLabWork(), args);
        } catch (MustBeEmptyException e) {
            return new ResponseWithTreeSet(ResponseStatusEnum.MUST_BE_WITHOUT_AN_ARGUMENT, collectionManager.getLabWork(), null);

        }
    }
}
