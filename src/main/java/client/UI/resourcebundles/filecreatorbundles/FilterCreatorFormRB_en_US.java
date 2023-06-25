package client.UI.resourcebundles.filecreatorbundles;

import java.util.ListResourceBundle;

public class FilterCreatorFormRB_en_US extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        Object[][] content = {
                {"filterColumnLabel", "Select filtering column:"},
                {"signLabel", "Select sign:"},
                {"valueForFilteringLabel", "Enter value for filtering:"},
                {"columnsForFilteringComboBox", "columns"},
                {"signsCombobox", "signs"},
                {"filteringValueTextField", "value here"},
                {"createButton", "Create"},
                {"cancelButton", "Cancel"}
        };
        return content;
    }
}
