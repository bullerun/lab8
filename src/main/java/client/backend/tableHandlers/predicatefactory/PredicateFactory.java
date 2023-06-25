package client.backend.tableHandlers.predicatefactory;

import common.data.LabWork;

import java.util.function.Predicate;

public abstract class PredicateFactory {
    public abstract Predicate<LabWork> createPredicate(FilterSigns filterSign, String value);
}
