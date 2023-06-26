package client.UI.resourcebundles.enums;

import client.UI.controllers.MainFormController;

import java.util.ResourceBundle;

public enum RuntimeOutputs {
    VALUE_WAS_NOT_ENTERED("valueWasNotEntered"),
    SIGN_WAS_NOT_SELECTED("signWasNotSelected"),
    COLUMN_WAS_NOT_SELECTED("columnWasNotSelected"),
    MODEL_WAS_NOT_SELECTED_IN_TABLE("modelWasNotSelectedInTable"),
    FIELDS_DOES_NOT_VALID("anyFieldsDoesNotValid"),
    FILE_LISTENER_CAN_NOT_READ_FILE("fileListenerCanNotReadFromFile"),
    FILE_LISTENER_PROBLEM_WITH_SCRIPT_FILE("fileListenerProblemWithScriptFile"),
    CAN_NOT_INIT_COMPONENT("canNonInitComponent");
    private final String bundleObjectName;

    RuntimeOutputs(String bundleObjectName){
        this.bundleObjectName = bundleObjectName;
    }

    @Override
    public String toString() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("client.UI.resourcebundles.runtimeoutputsbundles.RuntimeOutputRB", MainFormController.getCurrentLocale().get().getLocale());
        return resourceBundle.getString(bundleObjectName);
    }
}
