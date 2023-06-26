package client.UI.resourcebundles.authorizationformbundles.errors;

import java.util.ListResourceBundle;

public class AuthorizationErrorsRB_en_US extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        Object[][] contents = {
                {"nameLength", "The user name must be less than 127 characters"},
                {"passwordLength", "The password must be equal to 8 characters"},
                {"empty", "Fields must not be empty"},
                {"error", "Failed to authorize the user"},
                {"nameIsTaking", "This login already exists"},
                {"invalid", "Invalid username or password"}
        };
        return contents;
    }
}
