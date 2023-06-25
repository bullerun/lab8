package server.command;


import common.Response;
import common.ResponseWithTreeSet;
import common.data.LabWork;
import common.exception.MustBeEmptyException;
import common.exception.MustBeNotEmptyException;
import server.manager.CollectionManager;

import java.util.NavigableSet;
/**
 * command to output the values of the discipline field of all laboratory works in descending order
 *
 * @author Nikita and Vlad
 * @version 0.1
 */
public class PrintFieldDescendingDisciplineCommand extends AbstractCommand {
    CollectionManager collectionManager;

    public PrintFieldDescendingDisciplineCommand(CollectionManager collectionManager) {
        super("print_field_descending_discipline", "выводит значения поля discipline всех элементов в порядке убывания");
        this.collectionManager = collectionManager;
    }

    @Override
    public ResponseWithTreeSet execute(String argument, Long client) {
        try {
            if (!argument.isEmpty()) throw new MustBeEmptyException();
            NavigableSet<LabWork> labWork = collectionManager.getLabWork().descendingSet();
            if (labWork.isEmpty()) throw new MustBeNotEmptyException();
            for (LabWork i : labWork) {
                if (i.getDiscipline() == null) {
                    return new ResponseWithTreeSet("У " + i.getId() + " " + i.getName() + " не указана дисциплина", collectionManager.getLabWork());
                } else {
                    return new ResponseWithTreeSet("У " + i.getId() + " " + i.getName() + " дисциплина " + i.getDiscipline(),collectionManager.getLabWork());
                }
            }
            
        } catch (MustBeEmptyException e) {
            return new ResponseWithTreeSet("Команда вводится без аргумента", collectionManager.getLabWork());

        } catch (MustBeNotEmptyException e) {
            return new ResponseWithTreeSet("Коллекция пуста",collectionManager.getLabWork());
            
        }
        return null;
    }
}