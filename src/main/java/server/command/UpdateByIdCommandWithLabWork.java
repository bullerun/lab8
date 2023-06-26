package server.command;


import common.ResponseStatusEnum;
import common.ResponseWithTreeSet;
import common.data.LabWork;
import server.manager.CollectionManager;
import server.manager.SqlCollectionManager;

public class UpdateByIdCommandWithLabWork extends AbstractCommandWithLabWork {

    private final CollectionManager collectionManager;
    private final SqlCollectionManager sqlCollectionManager;

    public UpdateByIdCommandWithLabWork(CollectionManager collectionManager, SqlCollectionManager sqlCollectionManager) {
        super("update");
        this.collectionManager = collectionManager;
        this.sqlCollectionManager = sqlCollectionManager;
    }

    @Override
    public ResponseWithTreeSet execute(LabWork labWork, Long client) {
        try {
            sqlCollectionManager.update(labWork, client);
            collectionManager.update(labWork, client);
            return new ResponseWithTreeSet(ResponseStatusEnum.GOOD_UPDATE, collectionManager.getLabWork(), null);
        } catch (Exception e) {
            return new ResponseWithTreeSet(ResponseStatusEnum.BAD_UPDATE, null, null);
        }
    }
}
