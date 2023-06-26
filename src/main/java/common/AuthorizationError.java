package common;

import client.UI.controllers.MainFormController;

import java.io.Serializable;
import java.util.ResourceBundle;

public enum AuthorizationError implements Serializable {


    NAME_LENGTH("nameLength"),

    PASSWORD_LENGTH("passwordLength"),

    EMPTY("empty"),
    ERROR("error"),
    NAME_IS_TAKING("nameIsTaking"),
    INVALID_USERNAME_OR_PASSWORD("invalid");



    private final String bundleObjectName;

    AuthorizationError(String bundleObjectName) {
        this.bundleObjectName = bundleObjectName;
    }

    @Override
    public String toString() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("client.UI.resourcebundles.authorizationformbundles.AuthorizationErrorsRB", MainFormController.getCurrentLocale().get().getLocale());
        return resourceBundle.getString(bundleObjectName);
    }
}


