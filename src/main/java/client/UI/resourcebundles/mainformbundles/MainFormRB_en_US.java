package client.UI.resourcebundles.mainformbundles;

import java.util.ListResourceBundle;

public class MainFormRB_en_US extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        Object[][] contents = {
                {"createFilterButton", "Create filter"},
                {"removeFiltersButton", "Remove filters"},
                {"addButton", "Add"},
                {"updateButton", "Update"},
                {"removeButton", "Remove"},
                {"removeByIdButton", "Remove by id"},
                {"removeLower", "Remove lower"},
                {"removeGreater", "Remove greater"},
                {"clearButton", "clear"},
                {"controllersLabel", "Controllers"},
                {"infoMenu", "Info"},
                {"settingsMenu", "Settings"},
                {"helpMenu", "Help"},
                {"languageMenuItem", "Language"},
                {"logOutMenuItem", "Log out"},
                {"executeScriptButton", "Execute script"},
                {"visualizeButton", "Visualize"}
        };
        return contents;
    }
}
