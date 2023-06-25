package client.backend.tableHandlers.predicatefactory.implementations;

import client.backend.tableHandlers.predicatefactory.FilterSigns;
import client.backend.tableHandlers.predicatefactory.PredicateFactory;
import common.data.LabWork;


import java.util.Objects;
import java.util.function.Predicate;

public class CoordinateYColumnPredicateFactory extends PredicateFactory {
    @Override
    public Predicate<LabWork> createPredicate(FilterSigns filterSign, String value) {
        try{
            return switch (filterSign){
                case MORE_THAN -> createMorePredicate(Long.parseLong(value));
                case EQUALITY -> createEqualPredicate(Long.parseLong(value));
                case INEQUALITY -> createInequalPredicate(Long.parseLong(value));
                case LESS_THAN -> createLessPredicate(Long.parseLong(value));
            };
        }
        catch (NumberFormatException numberFormatException){
            return value == null ? Objects::isNull : x->false;
        }
    }

    private Predicate<LabWork> createMorePredicate(Long value){
        return (labwork) -> labwork.getCoordinates().getY() > value;
    }

    private Predicate<LabWork> createLessPredicate(Long value){
        return (labwork) -> labwork.getCoordinates().getY() < value;
    }

    private Predicate<LabWork> createEqualPredicate(Long value){
        return (labwork) -> Objects.equals(labwork.getCoordinates().getY(), value);
    }

    private Predicate<LabWork> createInequalPredicate(Long value){
        return (labwork) -> !Objects.equals(labwork.getCoordinates().getY(), value);
    }
}
