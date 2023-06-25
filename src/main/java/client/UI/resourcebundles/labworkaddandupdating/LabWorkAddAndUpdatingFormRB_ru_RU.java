package client.UI.resourcebundles.labworkaddandupdating;

import java.util.ListResourceBundle;

public class LabWorkAddAndUpdatingFormRB_ru_RU extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        Object[][] contents = {
                {"labWorkNameTextField", "название лабораторной работы"},
                {"coordinateXTextField", "координата X"},
                {"coordinateYTextField", "координата Y"},
                {"minimalPointTextField", "минимальный балл"},
                {"difficultyComboBox", "сложность"},
                {"disciplineNameTextField", "название дисциплины"},
                {"practiceHoursTextField", "количество часов"},


                {"labWorkNameLabel", "название лабораторной работы"},
                {"coordinateXLabel", "координата X"},
                {"coordinateYLabel", "координата Y"},
                {"minimalPointLabel", "минимальный балл"},
                {"difficultyLabel", "сложность"},
                {"disciplineNameLabel", "название дисциплины"},
                {"practiceHoursLabel", "количество часов"},


                {"cancelButton", "отмена"},
                {"okButton", "ок"}
        };
        return contents;
    }
}