package client.UI.resourcebundles.authorizationformbundles.errors;

import java.util.ListResourceBundle;

public class AuthorizationErrorsRB_ro_RO extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        Object[][] contents = {
                {"nameLength", "Numele de utilizator trebuie să aibă mai puțin de 127 de caractere"},
                {"passwordLength", "Parola trebuie să fie egală cu 8 caractere"},
                {"empty", "Câmpurile nu trebuie să fie goale"},
                {"error", "Nu a reușit să autorizeze utilizatorul"},
                {"nameIsTaking", "Această autentificare există deja"},
                {"invalid", "Nume de utilizator sau parolă nevalide"}
        };
        return contents;
    }
}
