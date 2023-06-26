package client.UI.resourcebundles.authorizationformbundles;

import java.util.ListResourceBundle;

public class AuthorizationFormRB_ro_RO extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        Object[][] contents = {
                {"authorizationLabel", "Autorizare"},
                {"logInTextField", "Introduceți numele dvs. de utilizator"},
                {"passwordTextField", "Introduceți parola"},
                {"settingsMenu", "Setări"},
                {"languageMenuItem", "Limba"},
                {"loginLabel", "Autentificare"},
                {"passwordLabel", "Parolă"},
                {"signInButton", "Intră"},
                {"signUpButton", "Înregistrare"}
        };
        return contents;
    }
}
