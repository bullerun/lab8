package server.command;

import common.ResponseStatusEnum;
import common.ResponseWithTreeSet;
import common.exception.MustBeNotEmptyException;
import server.manager.CollectionManager;
import server.manager.SqlCollectionManager;

import java.sql.SQLException;

/**
 * command that deletes the specified laboratory work
 *
 * @author Nikita and Vlad
 * @version 0.1
 */
public class RemoveByIdCommand extends AbstractCommand {
    private final CollectionManager collectionManager;
    private final SqlCollectionManager sqlCollectionManager;

    public RemoveByIdCommand(CollectionManager collectionManager, SqlCollectionManager sqlCollectionManager) {
        super("remove_by_id id", "удаляет элемент из коллекции по его id");
        this.collectionManager = collectionManager;
        this.sqlCollectionManager = sqlCollectionManager;
    }

    @Override
    public ResponseWithTreeSet execute(String argument, Long client) {
        try {
            if (argument.isEmpty()) throw new MustBeNotEmptyException();
            sqlCollectionManager.removeByID(Long.parseLong(argument.trim()), client);
            if (collectionManager.removeByID(Long.parseLong(argument.trim()), client)) {
                return new ResponseWithTreeSet(ResponseStatusEnum.LABORATORY_WORK_WITH_THIS_ID_HAS_BEEN_DELETED,collectionManager.getLabWork());
            }
            return new ResponseWithTreeSet(ResponseStatusEnum.LACK_OF_RIGHTS_OR_OR_LABORATORY_DOES_NOT_EXIST,collectionManager.getLabWork());
        } catch (MustBeNotEmptyException e) {
            return new ResponseWithTreeSet(ResponseStatusEnum.LACK_OF_ARGUMENT,collectionManager.getLabWork());

        } catch (NumberFormatException e) {
            return new ResponseWithTreeSet(ResponseStatusEnum.INVALID_ARGUMENT,collectionManager.getLabWork());
        } catch (SQLException e) {
            return new ResponseWithTreeSet(ResponseStatusEnum.NO_SUCH_LABORATORY,collectionManager.getLabWork());
        } catch (Exception e) {
            return new ResponseWithTreeSet(ResponseStatusEnum.ERROR,collectionManager.getLabWork());
        }
    }
}
