package client.backend.tableHandlers.predicatefactory.implementations;

import client.backend.tableHandlers.predicatefactory.FilterSigns;
import client.backend.tableHandlers.predicatefactory.PredicateFactory;
import common.data.LabWork;

import java.util.function.Predicate;

public class NameColumnPredicateFactory extends PredicateFactory {
    @Override
    public Predicate<LabWork> createPredicate(FilterSigns filterSign, String value) {
        return switch (filterSign){
            case MORE_THAN -> createMorePredicate(value);
            case EQUALITY -> createEqualPredicate(value);
            case INEQUALITY -> createInequalPredicate(value);
            case LESS_THAN -> createLessPredicate(value);
        };
    }

    private Predicate<LabWork> createMorePredicate(String value){
        return (labwork) -> labwork.getName().compareTo(value)>0;
    }

    private Predicate<LabWork> createLessPredicate(String value){
        return (labwork) -> labwork.getName().compareTo(value)<0;
    }

    private Predicate<LabWork> createEqualPredicate(String value){
        return (labwork) -> labwork.getName().equals(value);
    }

    private Predicate<LabWork> createInequalPredicate(String value){
        return (labwork) -> !labwork.getName().equals(value);
    }
}
