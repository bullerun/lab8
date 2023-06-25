package server.command;


import common.Response;
import common.ResponseWithTreeSet;
import common.data.LabWork;
import common.exception.MustBeEmptyException;
import server.manager.CollectionManager;

/**
 * class sum of minimal point command
 *
 * @author Nikita and Vlad
 * @version 0.1
 */
public class SumOfMinimalPointCommand extends AbstractCommand {
    CollectionManager collectionManager;

    public SumOfMinimalPointCommand(CollectionManager collectionManager) {
        super("sum_of_minimal_point", "выводит сумму значений поля minimalPoint для всех элементов коллекции");
        this.collectionManager = collectionManager;
    }

    @Override
    public ResponseWithTreeSet execute(String argument, Long client) {
        try {
            if (!argument.isEmpty()) throw new MustBeEmptyException();
            long SumOfMinimalPoints = 0;
            for (LabWork i : collectionManager.getLabWork()) {
                SumOfMinimalPoints += i.getMinimalPoint();
            }
            return new ResponseWithTreeSet("сумму значений поля minimalPoint для всех элементов коллекции = " + SumOfMinimalPoints,collectionManager.getLabWork());
            
        } catch (MustBeEmptyException e) {
            return new ResponseWithTreeSet("Команда вводится без аргумента",collectionManager.getLabWork());
            
        }
    }
}