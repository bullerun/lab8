package client.UI.resourcebundles.mainformbundles;

import java.util.ListResourceBundle;

public class MainFormRB_ro_RO extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        Object[][] contents = {
                {"createFilterButton", "Creează filtru"},
                {"removeFiltersButton", "Elimină filtru"},
                {"addButton", "Adaugă"},
                {"updateButton", "Actualizare"},
                {"removeButton", "Elimină"},
                {"removeByIdButton", "Eliminați prin id"},
                {"removeLower", "Scoateți partea inferioară"},
                {"removeGreater", "Eliminați mai mare"},
                {"clearButton", "Clar"},
                {"controllersLabel", "Controlere"},
                {"infoMenu", "Info"},
                {"settingsMenu", "Setări"},
                {"helpMenu", "Ajutor"},
                {"languageMenuItem", "Limba"},
                {"logOutMenuItem", "Deconectare"},
                {"executeScriptButton", "Executați scriptul"},
                {"visualizeButton", "Vizualizează"}
        };
        return contents;
    }
}
