package client.backend.tableHandlers.predicatefactory.implementations;

import client.backend.tableHandlers.predicatefactory.FilterSigns;
import client.backend.tableHandlers.predicatefactory.PredicateFactory;
import common.data.LabWork;

import java.util.Objects;
import java.util.function.Predicate;

public class PracticeHoursColumnPredicateFactory extends PredicateFactory {
    @Override
    public Predicate<LabWork> createPredicate(FilterSigns filterSign, String value) {
        try{
            return switch (filterSign){
                case MORE_THAN -> createMorePredicate(Integer.parseInt(value));
                case EQUALITY -> createEqualPredicate(Integer.parseInt(value));
                case INEQUALITY -> createInequalPredicate(Integer.parseInt(value));
                case LESS_THAN -> createLessPredicate(Integer.parseInt(value));
            };
        }
        catch (NumberFormatException numberFormatException){
            return value == null ? Objects::isNull : x->false;
        }
    }

    private Predicate<LabWork> createMorePredicate(int value){
        return (labwork) -> labwork.getDiscipline()!= null & labwork.getDiscipline().getPracticeHours() > value;
    }

    private Predicate<LabWork> createLessPredicate(int value){
        return (labwork) -> labwork.getDiscipline()!= null & labwork.getDiscipline().getPracticeHours() < value;
    }

    private Predicate<LabWork> createEqualPredicate(int value){
        return (labwork) -> labwork.getDiscipline()!= null & labwork.getDiscipline().getPracticeHours() == value;
    }

    private Predicate<LabWork> createInequalPredicate(int value){
        return (labwork) -> labwork.getDiscipline()!= null & labwork.getDiscipline().getPracticeHours() != value;
    }
}