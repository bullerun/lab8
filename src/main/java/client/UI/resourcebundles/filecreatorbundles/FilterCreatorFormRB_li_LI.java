package client.UI.resourcebundles.filecreatorbundles;

import java.util.ListResourceBundle;

public class FilterCreatorFormRB_li_LI extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        Object[][] content = {
                {"filterColumnLabel", "Pasirinkite filtravimo stulpelį:"},
                {"signLabel", "Pasirinkite ženklą:"},
                {"valueForFilteringLabel", "Įveskite filtravimo vertę:"},
                {"columnsForFilteringComboBox", "stulpelis"},
                {"signsCombobox", "pasirašyti"},
                {"filteringValueTextField", "vertė čia"},
                {"createButton", "Sukurti"},
                {"cancelButton", "Atšaukti"}
        };
        return content;
    }
}
