package client.UI.resourcebundles.availablelocalesbundles;

import java.util.ListResourceBundle;

public class AvailableLocalesRB_ro_RO extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        Object[][] contents = {
                {"russian", "Rusă"},
                {"english", "Engleză"},
                {"romanian","Română"},
                {"lithuanian", "Lituaniană"}
        };
        return contents;
    }
}
