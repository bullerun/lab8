package server.command;

import common.Response;
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
                return new ResponseWithTreeSet("при вызове команды remove_lower удалено " + count + " лабораторных работ", collectionManager.getLabWork());
            }
            return new ResponseWithTreeSet("Удаление не было осуществлено, проверьте наличие лабораторных удовлетворяющих условию и права на эти лабораторные работы", collectionManager.getLabWork());
        } catch (MustBeNotEmptyException e) {
            return new ResponseWithTreeSet("Id не введен", collectionManager.getLabWork());

        } catch (NullPointerException e) {
            return new ResponseWithTreeSet("Лабораторной работы с таким Id отсутствует", collectionManager.getLabWork());

        } catch (NumberFormatException e) {
            return new ResponseWithTreeSet("Некорректный ввод", collectionManager.getLabWork());

        } catch (Exception e) {
            return new ResponseWithTreeSet("Ошибка при удалении", collectionManager.getLabWork());
        }
    }
}