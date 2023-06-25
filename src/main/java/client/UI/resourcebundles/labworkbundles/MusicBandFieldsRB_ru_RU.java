package client.UI.resourcebundles.labworkbundles;


import java.util.ListResourceBundle;

public class MusicBandFieldsRB_ru_RU extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        Object[][] resources = {
                {"id", "ID"},
                {"ownerId", "ID владельца"},
                {"creationDate", "Дата создания"},
                {"name", "имя группы"},
                {"coordinateX", "Координата X"},
                {"coordinateY", "Координата Y"},
                {"minimalPoint", "минимальный балл"},
                {"difficulty", "сложность"},
                {"disciplineName", "название дисциплины"},
                {"practiceHours", "количество часов"}
        };

        return resources;
    }
}
