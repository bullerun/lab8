package client.backend.tableHandlers.predicatefactory.implementations;

import client.backend.tableHandlers.predicatefactory.FilterSigns;
import client.backend.tableHandlers.predicatefactory.PredicateFactory;
import common.data.LabWork;


import java.util.Objects;
import java.util.function.Predicate;

public class CoordinateXColumnPredicateFactory extends PredicateFactory {
    @Override
    public Predicate<LabWork> createPredicate(FilterSigns filterSign, String value) {
        try {
            return switch (filterSign){
                case MORE_THAN -> createMorePredicate(Float.parseFloat(value));
                case EQUALITY -> createEqualPredicate(Float.parseFloat(value));
                case INEQUALITY -> createInequalPredicate(Float.parseFloat(value));
                case LESS_THAN -> createLessPredicate(Float.parseFloat(value));
            };
        }
        catch (NumberFormatException numberFormatException){
            return value == null ? Objects::isNull : x->false;
        }
    }

    private Predicate<LabWork> createMorePredicate(float value){
        return (labwork) -> labwork.getCoordinates().getX() > value;
    }

    private Predicate<LabWork> createLessPredicate(float value){
        return (labwork) -> labwork.getCoordinates().getX() < value;
    }

    private Predicate<LabWork> createEqualPredicate(float value){
        return (labwork) -> labwork.getCoordinates().getX() == value;
    }

    private Predicate<LabWork> createInequalPredicate(float value){
        return (labwork) -> labwork.getCoordinates().getX()!=value;
    }
}
