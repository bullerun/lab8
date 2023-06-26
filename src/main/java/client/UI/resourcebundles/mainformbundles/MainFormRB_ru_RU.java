package client.UI.resourcebundles.mainformbundles;

import java.util.ListResourceBundle;

public class MainFormRB_ru_RU extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        Object[][] contents = {
                {"createFilterButton", "Создать фильтр"},
                {"removeFiltersButton", "Убрать фильтр"},
                {"addButton", "Добавить"},
                {"updateButton", "Обновить"},
                {"removeButton", "Удалить"},
                {"removeByIdButton", "Удалить по ID"},
                {"removeLower", "Удалить меньшие"},
                {"removeGreater", "Удалить большие"},
                {"clearButton", "Очистить"},
                {"controllersLabel", "Контроллеры"},
                {"infoMenu", "Информация"},
                {"settingsMenu", "Настройки"},
                {"helpMenu", "Помощь"},
                {"languageMenuItem", "Язык"},
                {"logOutMenuItem", "Выйти"},
                {"executeScriptButton", "Выполнить скрипт"},
                {"visualizeButton", "Визуализировать"},
                {"sumOfMinimalPoint", "Сумма минимальных баллов"},
                {"averageOfMinimalPoint", "Среднее значение минимальных баллов"}
        };
        return contents;
    }
}
