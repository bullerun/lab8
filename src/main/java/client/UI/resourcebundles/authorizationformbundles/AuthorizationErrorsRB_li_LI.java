package client.UI.resourcebundles.authorizationformbundles;

import java.util.ListResourceBundle;

public class AuthorizationErrorsRB_li_LI extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        Object[][] contents = {
                {"nameLength", "Vartotojo vardas turi būti mažesnis nei 127 simboliai"},
                {"passwordLength", "Slaptažodis turi būti lygus 8 simboliams"},
                {"empty", "Laukai neturi būti tušti"},
                {"error", "Nepavyko autorizuoti vartotojo"}
        };
        return contents;
    }
}
