package client.UI.resourcebundles.enums;

import client.UI.controllers.MainFormController;

import java.util.ResourceBundle;

public enum LabWorkAddAndUpdatingFormElements {

    LAB_WORK_NAME_TEXT_FIELD("labWorkNameTextField"),
    COORDINATE_X_TEXT_FIELD("coordinateXTextField"),
    COORDINATE_Y_TEXT_FIELD("coordinateYTextField"),
    MINIMAL_POINT("minimalPointTextField"),
    DIFFICULTY_COMBO_BOX("difficultyComboBox"),
    DISCIPLINE_NAME_TEXT_FIELD("disciplineNameTextField"),
    PRACTICE_HOURS_TEXT_FIELD("practiceHoursTextField"),
    LAB_WORK_NAME_LABEL("labWorkNameLabel"),
    COORDINATE_X_LABEL("coordinateXLabel"),
    COORDINATE_Y_LABEL("coordinateYLabel"),
    MINIMAL_POINT_LABEL("minimalPointLabel"),
    DIFFICULTY_LABEL("difficultyLabel"),
    DISCIPLINE_NAME_LABEL("disciplineNameLabel"),
    PRACTICE_HOURS_LABEL("practiceHoursLabel"),
    OK_Button("okButton"),
    CANCEL_BUTTON("cancelButton");
    private final String bundleObjectName;

    LabWorkAddAndUpdatingFormElements(String bundleObjectName){
        this.bundleObjectName = bundleObjectName;
    }

    @Override
    public String toString() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("client.UI.resourcebundles.labworkaddandupdating.LabWorkAddAndUpdatingFormRB", MainFormController.getCurrentLocale().get().getLocale());
        return resourceBundle.getString(bundleObjectName);
    }
}
