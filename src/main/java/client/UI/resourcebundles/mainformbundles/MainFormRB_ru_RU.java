package client.UI.resourcebundles.mainformbundles;

import java.util.ListResourceBundle;

public class MainFormRB_ru_RU extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        Object[][] contents = {
                {"createFilterButton", "Создать фильтр"},
                {"removeFiltersButton", "Удалить фильтры"},
                {"addButton", "Добавить"},
                {"addIfMinButton", "Добавить если минимально"},
                {"updateButton", "Обновить"},
                {"removeButton", "Удалить"},
                {"removeByIdButton", "Удалить по id"},
                {"removeByIdButton", "Remove by id"},
                {"removeLower", "Удалить если меньше"},
                {"removeGreater", "Удалить если больше"},
                {"clearButton", "Очистить"},
                {"filterLessThanFrontManButton", "Фильтровать меньших чем солист"},
                {"countGreaterThanFrontManButton", "Количество больших чем солист"},
                {"groupCountingByCoordinatesButton", "Группировать по координатам"},
                {"controllersLabel", "Контроллеры"},
                {"infoMenu", "инфо"},
                {"settingsMenu", "Настройки"},
                {"helpMenu", "Помощь"},
                {"languageMenuItem", "Язык"},
                {"logOutMenuItem", "Выйти"},
                {"executeScriptButton", "Запустить скрипт"},
                {"visualizeButton", "Визуализация"}
        };
        return contents;
    }
}
