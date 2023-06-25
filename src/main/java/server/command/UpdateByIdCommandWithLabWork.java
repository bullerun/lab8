package server.command;


import common.Response;
import common.ResponseWithLabWork;
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
            return new ResponseWithTreeSet("лабораторная работа обновлена", collectionManager.getLabWork());
        } catch (Exception e) {
            return new ResponseWithTreeSet("не получилось обновить лабораторную работу", null);
        }
    }
}
