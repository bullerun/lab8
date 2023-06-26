package client.UI.resourcebundles.labworkbundles;

import java.util.ListResourceBundle;

public class MusicBandFieldsRB_ro_RO extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        Object[][] resources = {
                {"id", "ID"},
                {"ownerId", "Id proprietar"},
                {"creationDate", "Data creării"},
                {"name", "Numele LabWork"},
                {"coordinateX", "Coordonata X"},
                {"coordinateY", "Coordonata Y"},
                {"minimalPoint", "Punct minim"},
                {"difficulty", "Dificultate"},
                {"disciplineName", "numele disciplinei"},
                {"practiceHours", "ore de practică"}

        };

        return resources;
    }
}
