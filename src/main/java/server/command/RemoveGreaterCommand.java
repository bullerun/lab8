package server.command;

import common.ResponseStatusEnum;
import common.ResponseWithTreeSet;
import common.exception.MustBeNotEmptyException;
import server.manager.CollectionManager;
import server.manager.SqlCollectionManager;


/**
 * command that deletes all laboratory works is greater than the entered
 *
 * @author Nikita and Vlad
 * @version 0.1
 */
public class RemoveGreaterCommand extends AbstractCommand {
    private final CollectionManager collectionManager;
    private final SqlCollectionManager sqlCollectionManager;

    public RemoveGreaterCommand(CollectionManager collectionManager, SqlCollectionManager sqlCollectionManager) {
        super("remove_greater {element}", "удаляет из коллекции все элементы, превышающие заданный");
        this.collectionManager = collectionManager;
        this.sqlCollectionManager = sqlCollectionManager;
    }

    @Override
    public ResponseWithTreeSet execute(String argument, Long client) {
        try {
            if (argument.isEmpty()) throw new MustBeNotEmptyException();
            sqlCollectionManager.removeGreater(Long.parseLong(argument.trim()), client);
            var count = collectionManager.removeGreater(Long.parseLong(argument.trim()), client);
            if (count > 0) {
                return new ResponseWithTreeSet(ResponseStatusEnum.REMOVE_GREATER, collectionManager.getLabWork());
            }
            return new ResponseWithTreeSet(ResponseStatusEnum.LACK_OF_RIGHTS_OR_OR_LABORATORY_DOES_NOT_EXIST,collectionManager.getLabWork());


        } catch (MustBeNotEmptyException e) {
            return new ResponseWithTreeSet(ResponseStatusEnum.LACK_OF_ARGUMENT, collectionManager.getLabWork());

        } catch (NullPointerException e) {
            return new ResponseWithTreeSet(ResponseStatusEnum.NO_SUCH_LABORATORY,collectionManager.getLabWork());

        } catch (NumberFormatException e) {
            return new ResponseWithTreeSet(ResponseStatusEnum.INVALID_ARGUMENT,collectionManager.getLabWork());

        } catch (Exception e) {
            return new ResponseWithTreeSet(ResponseStatusEnum.ERROR,collectionManager.getLabWork());
        }
    }
}