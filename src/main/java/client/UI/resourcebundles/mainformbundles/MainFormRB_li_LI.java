package client.UI.resourcebundles.mainformbundles;

import java.util.ListResourceBundle;

public class MainFormRB_li_LI extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        Object[][] contents = {
                {"createFilterButton", "Sukurti filtrą"},
                {"removeFiltersButton", "Pašalinti filtrus"},
                {"addButton", "Pridėti"},
                {"updateButton", "Naujinimas"},
                {"removeButton", "Pašalinti"},
                {"removeByIdButton", "Pašalinti pagal id"},
                {"removeLower", "Pašalinti mažesnis"},
                {"removeGreater", "Pašalinti didesnį"},
                {"clearButton", "Aiškus"},
                {"controllersLabel", "Valdytojas"},
                {"infoMenu", "Valdytojas"},
                {"settingsMenu", "Parametras"},
                {"helpMenu", "Padėti"},
                {"languageMenuItem", "Kalba"},
                {"logOutMenuItem", "Atsijungti"},
                {"executeScriptButton", "Vykdyti scenarijų"},
                {"visualizeButton", "Vizualizuoti"}
        };
        return contents;
    }
}
