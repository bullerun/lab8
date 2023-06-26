package client.UI.resourcebundles.authorizationformbundles;

import java.util.ListResourceBundle;

public class AuthorizationFormRB_li_LI extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        Object[][] contents = {
                {"authorizationLabel", "Leidimas"},
                {"logInTextField", "Įveskite prisijungimą čia"},
                {"passwordTextField", "Įveskite pass čia"},
                {"settingsMenu", "Parametras"},
                {"languageMenuItem", "Kalba"},
                {"loginLabel", "Prisijungimas"},
                {"passwordLabel", "Slaptažodis"},
                {"signInButton", "Prisijungti"},
                {"signUpButton", "užsiregistruoti"}
        };
        return contents;
    }
}
