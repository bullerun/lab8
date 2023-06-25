package server.command;

import common.Response;
import common.ResponseWithTreeSet;
import common.data.LabWork;
import common.exception.MustBeEmptyException;
import server.manager.CollectionManager;


/**
 * outputs the average value of the minimal Point field for all items in the collection
 *
 * @author Nikita and Vlad
 * @version 0.1
 */
public class AverageOfMinimalPointCommand extends AbstractCommand {
    CollectionManager collectionManager;

    public AverageOfMinimalPointCommand(CollectionManager collectionManager) {
        super("average_of_minimal_point", "выводит среднее значение поля minimalPoint для всех элементов коллекции");
        this.collectionManager = collectionManager;
    }

    @Override
    public ResponseWithTreeSet execute(String argument, Long client) {
        try {
            if (!argument.isEmpty()) throw new MustBeEmptyException();
            long SumOfMinimalPoints = 0;
            long count = 0;
            for (LabWork i : collectionManager.getLabWork()) {
                SumOfMinimalPoints += i.getMinimalPoint();
                count += 1;
            }
            return new ResponseWithTreeSet("среднее значение поля minimalPoint для всех элементов коллекции = " + SumOfMinimalPoints / count, collectionManager.getLabWork());

        } catch (MustBeEmptyException e) {
            return new ResponseWithTreeSet("Команда вводится без аргумента",collectionManager.getLabWork());

        }catch (ArithmeticException e){
            return new ResponseWithTreeSet("Нет лабораторных работ",collectionManager.getLabWork());

        }
    }
}