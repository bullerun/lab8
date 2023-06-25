package client.UI.resourcebundles.enums;

import client.UI.controllers.MainFormController;

import java.util.ResourceBundle;

public enum FilterCreatorFormElements {
    FILTER_COLUMN_LABEL("filterColumnLabel"),
    SIGN_LABEL("signLabel"),
    VALUE_FOR_FILTERING_LABEL("valueForFilteringLabel"),
    COLUMNS_FOR_FILTERING_COMBO_BOX("columnsForFilteringComboBox"),
    SIGNS_COMBO_BOX("signsCombobox"),
    FILTERING_VALUE_TEXT_FIELD("filteringValueTextField"),
    CREATE_BUTTON("createButton"),
    CANCEL_BUTTON("cancelButton");
    private final String bundleObjectName;

    FilterCreatorFormElements(String bundleObjectName){
        this.bundleObjectName = bundleObjectName;
    }

    @Override
    public String toString() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("client.UI.resourcebundles.filecreatorbundles.FilterCreatorFormRB", MainFormController.getCurrentLocale().get().getLocale());
        return resourceBundle.getString(bundleObjectName);
    }
}
