package client.UI.resourcebundles.visualformbundles;

import java.util.ListResourceBundle;

public class VisualizationFormRB_ru_RU extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        Object[][] contents = {
                {"backToTheTableButton", "Вернуться к таблице"},
                {"coordinateXLabel", "Координата X"},
                {"coordinateYLabel", "Координата Y"}
        };
        return contents;
    }
}
