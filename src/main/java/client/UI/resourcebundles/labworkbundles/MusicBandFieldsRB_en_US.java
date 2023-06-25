package client.UI.resourcebundles.labworkbundles;


import java.util.ListResourceBundle;

public class MusicBandFieldsRB_en_US extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        Object[][] resources = {
                {"id", "ID"},
                {"ownerId", "Owner ID"},
                {"creationDate", "Creation date"},
                {"name", "LabWork name"},
                {"coordinateX", "Coordinate X"},
                {"coordinateY", "Coordinate Y"},
                {"minimalPoint", "Minimal point"},
                {"difficulty", "difficulty"},
                {"disciplineName", "discipline name"},
                {"practiceHours", "practice hours"}

        };

        return resources;
    }
}
