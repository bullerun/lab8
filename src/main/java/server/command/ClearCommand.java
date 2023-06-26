package server.command;


import common.ResponseStatusEnum;
import common.ResponseWithTreeSet;
import common.exception.MustBeEmptyException;
import common.exception.MustBeNotEmptyException;
import server.manager.CollectionManager;
import server.manager.SqlCollectionManager;

/**
 * collection cleanup command
 *
 * @author Nikita and Vlad
 * @version 0.1
 */
public class ClearCommand extends AbstractCommand{
    private final CollectionManager collectionManager;
    private final SqlCollectionManager sqlCollectionManager;
    public ClearCommand(CollectionManager collectionManager, SqlCollectionManager sqlCollectionManager){
        super("clear", "очищает коллекцию");
        this.collectionManager = collectionManager;
        this.sqlCollectionManager = sqlCollectionManager;
    }
    @Override
    public ResponseWithTreeSet execute(String argument, Long client) {
        try {
            if (!argument.isEmpty()) throw new MustBeEmptyException();
            if (collectionManager.getLabWork().isEmpty()) throw new MustBeNotEmptyException();
            sqlCollectionManager.clear(client);
            collectionManager.clearCollection(client);
        } catch (MustBeEmptyException e) {
            return new ResponseWithTreeSet(ResponseStatusEnum.MUST_BE_WITHOUT_AN_ARGUMENT,collectionManager.getLabWork(), null);

        }catch (MustBeNotEmptyException e){
            return new ResponseWithTreeSet(ResponseStatusEnum.COLLECTION_IS_EMPTY,collectionManager.getLabWork(), null);
        }catch (Exception e){
            return new ResponseWithTreeSet(ResponseStatusEnum.ERROR_WHEN_TRYING_TO_CLEAR_THE_COLLECTION,collectionManager.getLabWork(), null);
        }
        return new ResponseWithTreeSet(ResponseStatusEnum.COLLECTION_CLEANUP_WAS_SUCCESSFUL,collectionManager.getLabWork(), null);
    }
}
