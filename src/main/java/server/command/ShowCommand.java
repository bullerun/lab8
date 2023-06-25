package server.command;


import common.ResponseWithTreeSet;
import common.exception.MustBeEmptyException;
import server.manager.CollectionManager;

/**
 * command class lab output command
 *
 * @author Nikita and Vlad
 * @version 0.1
 */
public class ShowCommand extends AbstractCommand{
    CollectionManager collectionManager;

    public ShowCommand(CollectionManager collectionManager){
        super("show", "выводит в стандартный поток вывода все элементы коллекции в строковом представлении");
        this.collectionManager =collectionManager;
    }
    @Override
    public ResponseWithTreeSet execute(String argument, Long client) {
        try {
            if (!argument.isEmpty()) throw new MustBeEmptyException();
            if (collectionManager.getLabWork().size() == 0) {
                return new ResponseWithTreeSet("Коллекция пуста", collectionManager.getLabWork());
            }

            return new ResponseWithTreeSet("", collectionManager.getLabWork());
            
        }catch (MustBeEmptyException e) {
            return new ResponseWithTreeSet("Команда вводится без аргумента", collectionManager.getLabWork());
        }
    }
}