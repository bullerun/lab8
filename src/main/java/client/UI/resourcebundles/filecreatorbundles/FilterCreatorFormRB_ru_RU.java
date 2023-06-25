package client.UI.resourcebundles.filecreatorbundles;

import java.util.ListResourceBundle;

public class FilterCreatorFormRB_ru_RU extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        Object[][] content = {
                {"filterColumnLabel", "Столбец для фильтрации:"},
                {"signLabel", "Знак фильтра:"},
                {"valueForFilteringLabel", "Значение фильтра:"},
                {"columnsForFilteringComboBox", "столбцы"},
                {"signsCombobox", "знаки"},
                {"filteringValueTextField", "значение тут"},
                {"createButton", "Создать"},
                {"cancelButton", "Отмена"}
        };
        return content;
    }
}
