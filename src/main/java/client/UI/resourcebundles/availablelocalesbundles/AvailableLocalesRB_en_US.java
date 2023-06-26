package client.UI.resourcebundles.availablelocalesbundles;

import java.util.ListResourceBundle;

public class AvailableLocalesRB_en_US extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        Object[][] contents = {
                {"russian", "Russian"},
                {"english", "English"},
                {"romanian","Romanian"},
                {"lithuanian", "Lithuanian"}
        };
        return contents;
    }
}
