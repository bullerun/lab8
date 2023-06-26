package server.command;

import common.ResponseStatusEnum;
import common.ResponseWithTreeSet;
import common.data.LabWork;
import server.manager.CollectionManager;
import server.manager.SqlCollectionManager;
import server.utility.LabAsk;

/**
 * add lab command
 *
 * @author Nikita and Vlad
 * @version 0.1
 */
public class AddCommand extends AbstractCommand {
    private final CollectionManager collectionManager;
    private final LabAsk labAsk;
    private final SqlCollectionManager sqlCollectionManager;

    public AddCommand(CollectionManager collectionManager, LabAsk labAsk, SqlCollectionManager sqlCollectionManager) {
        super("add", "добавляет новый элемент в коллекцию");
        this.collectionManager = collectionManager;
        this.labAsk = labAsk;
        this.sqlCollectionManager = sqlCollectionManager;
    }

    @Override
    public ResponseWithTreeSet execute(String argument, Long client) {
        try {
            LabWork labWork = labAsk.addLabWork();
            labWork.setOwnerID(client);
            labWork.setId(sqlCollectionManager.addInDB(labWork, client));
            collectionManager.addToCollection(labWork);
            return new ResponseWithTreeSet(ResponseStatusEnum.LAB_SUCCESSFULLY_ADDED,collectionManager.getLabWork());
        } catch (Exception e) {
            return new ResponseWithTreeSet(ResponseStatusEnum.LAB_NOT_ADDED,collectionManager.getLabWork());
        }
    }
}
