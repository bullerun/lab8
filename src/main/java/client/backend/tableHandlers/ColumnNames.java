package client.backend.tableHandlers;

import client.UI.controllers.MainFormController;

import java.util.Locale;
import java.util.ResourceBundle;

public enum ColumnNames {
    ID_COLUMN("id"),
    OWNER_ID_COLUMN("ownerId"),
    NAME_COLUMN("name"),
    COORDINATES_X_COLUMN("coordinateX"),
    COORDINATES_Y_COLUMN("coordinateY"),
    CREATION_DATE_COLUMN("creationDate"),
    MINIMAL_POINT("minimalPoint"),
    DIFFICULT("difficulty"),
    DISCIPLINE_NAME("disciplineName"),
    PRACTICE_HOURS("practiceHours");


    private final String bundleObjectName;

    ColumnNames(String bundleObjectName){
        this.bundleObjectName = bundleObjectName;
    }

    @Override
    public String toString() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("client.UI.resourcebundles.labworkbundles.MusicBandFieldsRB", MainFormController.getCurrentLocale().get().getLocale());
        return resourceBundle.getString(bundleObjectName);
    }
}
