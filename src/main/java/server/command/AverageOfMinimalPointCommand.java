package server.command;

import common.ResponseStatusEnum;
import common.ResponseWithTreeSet;
import common.data.LabWork;
import common.exception.MustBeEmptyException;
import server.manager.CollectionManager;

import java.util.ArrayList;


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
            ArrayList<String> args = new ArrayList<>();
            args.add(String.valueOf(SumOfMinimalPoints / count));
            return new ResponseWithTreeSet(ResponseStatusEnum.AVERAGE, collectionManager.getLabWork(), args);
        } catch (MustBeEmptyException e) {
            return new ResponseWithTreeSet(ResponseStatusEnum.MUST_BE_WITHOUT_AN_ARGUMENT, collectionManager.getLabWork(), null);

        } catch (ArithmeticException e) {
            return new ResponseWithTreeSet(ResponseStatusEnum.COLLECTION_IS_EMPTY, collectionManager.getLabWork(), null);

        }
    }
}