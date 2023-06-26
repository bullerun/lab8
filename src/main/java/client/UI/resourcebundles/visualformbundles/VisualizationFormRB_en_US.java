package client.UI.resourcebundles.visualformbundles;

import java.util.ListResourceBundle;

public class VisualizationFormRB_en_US extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        Object[][] contents = {
                {"backToTheTableButton", "Back to the table"},
                {"coordinateXLabel", "Coordinate X"},
                {"coordinateYLabel", "Coordinate Y"}
        };
        return contents;
    }
}
