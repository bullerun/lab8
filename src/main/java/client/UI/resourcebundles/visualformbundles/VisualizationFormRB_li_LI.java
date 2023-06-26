package client.UI.resourcebundles.visualformbundles;

import java.util.ListResourceBundle;

public class VisualizationFormRB_li_LI extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        Object[][] contents = {
                {"backToTheTableButton", "Grįžti į lentelę"},
                {"coordinateXLabel", "X koordinatė"},
                {"coordinateYLabel", "Y koordinatė"}
        };
        return contents;
    }
}