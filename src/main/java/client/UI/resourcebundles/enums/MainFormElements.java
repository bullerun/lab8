package client.UI.resourcebundles.enums;


import client.UI.controllers.MainFormController;

import java.util.ResourceBundle;

public enum MainFormElements {
    SETTINGS_MENU("settingsMenu"),
    LOG_OUT_MENU_ITEM("logOutMenuItem"),
    LANGUAGE_MENU_ITEM("languageMenuItem"),
    INFO_MENU("infoMenu"),
    CREATE_FILTER_BUTTON("createFilterButton"),
    REMOVE_FILTERS_BUTTON("removeFiltersButton"),
    ADD_BUTTON("addButton"),

    UPDATE_BUTTON("updateButton"),
    REMOVE_BUTTON("removeButton"),
    REMOVE_GREATER_BUTTON("removeGreater"),
    REMOVE_LOWER_BUTTON("removeLower"),
    REMOVE_BY_ID_BUTTON("removeByIdButton"),
    CLEAR_BUTTON("clearButton"),
    CONTROLLERS_LABEL("controllersLabel"),
    EXECUTE_SCRIPT_BUTTON("executeScriptButton"),
    VISUALIZE_BUTTON("visualizeButton"),
    SUM_OF_MINIMAL_POINT("sumOfMinimalPoint"),
    AVERAGE("averageOfMinimalPoint");
    private final String bundleObjectName;

    MainFormElements(String bundleObjectName){
        this.bundleObjectName = bundleObjectName;
    }

    @Override
    public String toString() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("client.UI.resourcebundles.mainformbundles.MainFormRB", MainFormController.getCurrentLocale().get().getLocale());
        return resourceBundle.getString(bundleObjectName);
    }
}
