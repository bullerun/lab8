package client.UI.resourcebundles.enums;

import client.UI.controllers.MainFormController;

import java.util.ResourceBundle;

public enum VisualisationFirmElements {
    BACK_TO_THE_TABLE_BUTTON("backToTheTableButton"),
    COORDINATE_X_LABEL("coordinateXLabel"),
    COORDINATE_Y_LABEL("coordinateYLabel");


    private final String bundleObjectName;

    VisualisationFirmElements(String bundleObjectName){
        this.bundleObjectName = bundleObjectName;
    }

    @Override
    public String toString() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("client.UI.resourcebundles.visualformbundles.VisualizationFormRB", MainFormController.getCurrentLocale().get().getLocale());
        return resourceBundle.getString(bundleObjectName);
    }
}
