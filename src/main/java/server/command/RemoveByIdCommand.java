package server.command;

import common.Response;
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
                return new ResponseWithTreeSet("Лабораторная работа c таким id удалена",collectionManager.getLabWork());
            }
            return new ResponseWithTreeSet("Удаление не было осуществлено, проверьте наличие данной лабораторной и права на эту лабораторную работу",collectionManager.getLabWork());
        } catch (MustBeNotEmptyException e) {
            return new ResponseWithTreeSet("Id не введен",collectionManager.getLabWork());

        } catch (NumberFormatException e) {
            return new ResponseWithTreeSet("некорректно введено число, число должно содержать только цифры и должно быть меньше или равно " + Long.MAX_VALUE,collectionManager.getLabWork());
        } catch (SQLException e) {
            return new ResponseWithTreeSet("такой лабораторной нет",collectionManager.getLabWork());
        } catch (Exception e) {
            return new ResponseWithTreeSet("Произошла неизвестная ошибка",collectionManager.getLabWork());
        }
    }
}
