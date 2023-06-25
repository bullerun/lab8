package client.UI.resourcebundles.authorizationformbundles;

import java.util.ListResourceBundle;

public class AuthorizationFormRB_en_US extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        Object[][] contents = {
                {"authorizationLabel", "Authorization"},
                {"logInTextField", "Enter login here"},
                {"passwordTextField", "Enter pass here"},
                {"settingsMenu", "Settings"},
                {"languageMenuItem", "Language"},
                {"loginLabel", "Login"},
                {"passwordLabel", "Password"},
                {"signInButton", "Sign In"},
                {"signUpButton", "Sign Up"}
        };
        return contents;
    }
}
