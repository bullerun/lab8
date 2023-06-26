package client.UI.resourcebundles.filecreatorbundles;

import java.util.ListResourceBundle;

public class FilterCreatorFormRB_ro_RO extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        Object[][] content = {
                {"filterColumnLabel", "Selectați coloana de filtrare:"},
                {"signLabel", "Selectați semnul:"},
                {"valueForFilteringLabel", "Introduceți valoarea pentru filtrare:"},
                {"columnsForFilteringComboBox", "coloane"},
                {"signsCombobox", "semne"},
                {"filteringValueTextField", "valoare aici"},
                {"createButton", "Creează"},
                {"cancelButton", "Anulează"}
        };
        return content;
    }
}
