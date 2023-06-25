package server.command;


import common.Response;
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
            return new ResponseWithTreeSet("Команда вводится без аргумента",collectionManager.getLabWork());

        }catch (MustBeNotEmptyException e){
            return new ResponseWithTreeSet("Коллекция и так пуста",collectionManager.getLabWork());
        }catch (Exception e){
            return new ResponseWithTreeSet("Произошла при попытке очистить коллекцию",collectionManager.getLabWork());
        }
        return new ResponseWithTreeSet("Все ваши лабораторные работы удалены",collectionManager.getLabWork());
    }
}
