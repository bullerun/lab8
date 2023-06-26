package client.UI.resourcebundles.runtimeoutputsbundles;

import java.util.ListResourceBundle;

public class RuntimeOutputRB_ru_RU extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        Object[][] content = {
                {"fileListenerCanNotReadFromFile", "Не удалось прочитать из файла!"},
                {"fileListenerProblemWithScriptFile", "Проблема с файлом скрипта..."},
                {"canNonInitComponent", "Не удалось инициализировать программный компонент!"},
                {"anyFieldsDoesNotValid", "Некорректные значения в полях!"},
                {"modelWasNotSelectedInTable", "Выберите модель в таблице."},
                {"columnWasNotSelected", "Выберите столбец!"},
                {"signWasNotSelected", "Выберите знак!"},
                {"valueWasNotEntered", "Введите значение!"}
        };
        return content;
    }
}
