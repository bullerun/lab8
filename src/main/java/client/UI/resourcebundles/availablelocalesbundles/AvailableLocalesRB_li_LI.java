package client.UI.resourcebundles.availablelocalesbundles;

import java.util.ListResourceBundle;

public class AvailableLocalesRB_li_LI extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        Object[][] contents = {
                {"russian", "Rusijos"},
                {"english", "Anglų"},
                {"romanian","Rumunų"},
                {"lithuanian", "Lietuvos"}
        };
        return contents;
    }
}
