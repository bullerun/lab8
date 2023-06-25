package client.UI.resourcebundles.authorizationformbundles;

import java.util.ListResourceBundle;

public class AuthorizationErrorsRB_en_US extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        Object[][] contents = {
                {"nameLength", "127"},
                {"passwordLength", "8"},
                {"empty", "must be not empty"},
                {"error", "error"}
        };
        return contents;
    }
}
