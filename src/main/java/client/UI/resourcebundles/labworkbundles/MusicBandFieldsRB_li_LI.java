package client.UI.resourcebundles.labworkbundles;

import java.util.ListResourceBundle;

public class MusicBandFieldsRB_li_LI extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        Object[][] resources = {
                {"id", "ID"},
                {"ownerId", "Savininko ID"},
                {"creationDate", "Sukūrimo data"},
                {"name", "Laboratorijos pavadinimas"},
                {"coordinateX", "Koordinatė X"},
                {"coordinateY", "Koordinatė Y"},
                {"minimalPoint", "Minimalus taškas"},
                {"difficulty", "Sunkumas"},
                {"disciplineName", "Disciplinos pavadinimas"},
                {"practiceHours", "Praktikos valandos"}

        };

        return resources;
    }
}
