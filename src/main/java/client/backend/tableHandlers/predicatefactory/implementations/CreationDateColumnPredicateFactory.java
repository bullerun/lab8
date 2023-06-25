package client.backend.tableHandlers.predicatefactory.implementations;

import client.backend.tableHandlers.predicatefactory.FilterSigns;
import client.backend.tableHandlers.predicatefactory.PredicateFactory;
import common.data.LabWork;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Objects;
import java.util.function.Predicate;

public class CreationDateColumnPredicateFactory extends PredicateFactory {
    @Override
    public Predicate<LabWork> createPredicate(FilterSigns filterSign, String value) {
        try {
            return switch (filterSign){
                case MORE_THAN -> createMorePredicate(LocalDate.parse(value));
                case EQUALITY -> createEqualPredicate(LocalDate.parse(value));
                case INEQUALITY -> createInequalPredicate(LocalDate.parse(value));
                case LESS_THAN -> createLessPredicate(LocalDate.parse(value));
            };
        }
        catch (DateTimeParseException dateTimeParseException){
            return value == null ? Objects::isNull : x->false;
        }
    }

    private Predicate<LabWork> createMorePredicate(LocalDate value){
        return (labwork) -> labwork.getCreationDate().isAfter(value);
    }

    private Predicate<LabWork> createLessPredicate(LocalDate value){
        return (labwork) -> labwork.getCreationDate().isBefore(value);
    }

    private Predicate<LabWork> createEqualPredicate(LocalDate value){
        return (labwork) -> labwork.getCreationDate().equals(value);
    }

    private Predicate<LabWork> createInequalPredicate(LocalDate value){
        return (labwork) -> !labwork.getCreationDate().equals(value);
    }
}
