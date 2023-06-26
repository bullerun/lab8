package server.command;


import common.ResponseStatusEnum;
import common.ResponseWithTreeSet;
import common.data.LabWork;
import server.manager.CollectionManager;
import server.manager.SqlCollectionManager;

public class AddCommandWithLabWork extends AbstractCommandWithLabWork {
    private final CollectionManager collectionManager;
    private final SqlCollectionManager sqlCollectionManager;

    public AddCommandWithLabWork(CollectionManager collectionManager, SqlCollectionManager sqlCollectionManager) {
        super("add");
        this.collectionManager = collectionManager;
        this.sqlCollectionManager = sqlCollectionManager;
    }

    @Override
    public ResponseWithTreeSet execute(LabWork labWork, Long client) {

        try {
            labWork.setId(sqlCollectionManager.addInDB(labWork, client));
            labWork.setOwnerID(client);
            collectionManager.addToCollection(labWork);
            return new ResponseWithTreeSet(ResponseStatusEnum.LAB_SUCCESSFULLY_ADDED, collectionManager.getLabWork(), null);
        } catch (Exception e) {
            return new ResponseWithTreeSet(ResponseStatusEnum.LAB_NOT_ADDED, null, null);
        }
    }
}
