package client.UI.resourcebundles.enums;



import client.UI.controllers.MainFormController;

import java.util.ResourceBundle;

public enum AuthorizationFormElements {
    AUTHORIZATION("authorizationLabel"),
    LOG_IN_TEXT_FIELD("logInTextField"),
    PASSWORD_TEXT_FIELD("passwordTextField"),
    SETTINGS_MENU("settingsMenu"),
    LANGUAGE_MENU_ITEM("languageMenuItem"),
    LOGIN_LABEL("loginLabel"),
    PASSWORD_LABEL("passwordLabel"),
    SIGN_IN_BUTTON("signInButton"),
    SIGN_UP_BUTTON("signUpButton");
    private final String bundleObjectName;

    AuthorizationFormElements (String bundleObjectName){
        this.bundleObjectName = bundleObjectName;
    }

    @Override
    public String toString() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("client.UI.resourcebundles.authorizationformbundles.AuthorizationFormRB", MainFormController.getCurrentLocale().get().getLocale());
        return resourceBundle.getString(bundleObjectName);
    }
}
