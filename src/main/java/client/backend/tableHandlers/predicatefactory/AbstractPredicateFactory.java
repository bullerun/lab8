package client.backend.tableHandlers.predicatefactory;

import client.backend.tableHandlers.ColumnNames;
import client.backend.tableHandlers.predicatefactory.implementations.*;
import common.data.Difficulty;
import common.data.Discipline;

public class AbstractPredicateFactory {
    public PredicateFactory createFactory(ColumnNames name){
        return switch (name){
            case ID_COLUMN -> new IdColumnPredicateFactory();
            case NAME_COLUMN -> new NameColumnPredicateFactory();
            case OWNER_ID_COLUMN -> new OwnerIdColumnPredicateFactory();
            case COORDINATES_X_COLUMN -> new CoordinateXColumnPredicateFactory();
            case COORDINATES_Y_COLUMN -> new CoordinateYColumnPredicateFactory();
            case CREATION_DATE_COLUMN -> new CreationDateColumnPredicateFactory();
            case MINIMAL_POINT -> new MinimalPointColumnPredicateFactory();
            case DIFFICULT -> new DifficultColumnPredicateFactory();
            case DISCIPLINE_NAME -> new DisciplineNameColumnPredicateFactory();
            case PRACTICE_HOURS  -> new PracticeHoursColumnPredicateFactory();

        };
    }
}
