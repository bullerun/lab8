package server.command;

import common.ResponseStatusEnum;
import common.ResponseWithTreeSet;
import common.exception.MustBeNotEmptyException;
import server.manager.CollectionManager;
import server.manager.SqlCollectionManager;


/**
 * command deleting all laboratory work less than the entered
 *
 * @author Nikita and Vlad
 * @version 0.1
 */
public class RemoveLowerCommand extends AbstractCommand {
    private final CollectionManager collectionManager;
    private final SqlCollectionManager sqlCollectionManager;

    public RemoveLowerCommand(CollectionManager collectionManager, SqlCollectionManager sqlCollectionManager) {
        super("remove_lower {element}", "удаляет из коллекции все элементы, меньшие, чем заданный");
        this.collectionManager = collectionManager;
        this.sqlCollectionManager = sqlCollectionManager;
    }

    @Override
    public ResponseWithTreeSet execute(String argument, Long client) {
        try {
            if (argument.isEmpty()) throw new MustBeNotEmptyException();
            sqlCollectionManager.removeLower(Long.parseLong(argument.trim()), client);
            var count = collectionManager.removeLower(Long.parseLong(argument.trim()), client);
            if (count >0) {
                return new ResponseWithTreeSet(ResponseStatusEnum.REMOVE_LOWER, collectionManager.getLabWork(), null);
            }
            return new ResponseWithTreeSet(ResponseStatusEnum.LACK_OF_RIGHTS_OR_OR_LABORATORY_DOES_NOT_EXIST, collectionManager.getLabWork(), null);
        } catch (MustBeNotEmptyException e) {
            return new ResponseWithTreeSet(ResponseStatusEnum.LACK_OF_ARGUMENT, collectionManager.getLabWork(), null);

        } catch (NullPointerException e) {
            return new ResponseWithTreeSet(ResponseStatusEnum.NO_SUCH_LABORATORY, collectionManager.getLabWork(), null);

        } catch (NumberFormatException e) {
            return new ResponseWithTreeSet(ResponseStatusEnum.INVALID_ARGUMENT, collectionManager.getLabWork(), null);

        } catch (Exception e) {
            return new ResponseWithTreeSet(ResponseStatusEnum.ERROR, collectionManager.getLabWork(), null);
        }
    }
}