package client.backend;

import client.UI.controllers.LabWorkAddAndUpdatingFormController;
import common.data.Coordinates;
import common.data.Difficulty;
import common.data.Discipline;
import common.exception.MustBeNotEmptyException;
import common.exception.RangeException;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * auxiliary class for working with an instance of the LabWork class
 *
 * @author Nikita and Vlad
 * @version 0.1
 */
public class LabAskStatic {
    private static final Long MINIMAL_POINT = 0L;
    private static final float MINIMAL_X_COORDINATES = -18;


    public static boolean checkName(TextField textField, LabWorkAddAndUpdatingFormController controller) {
        try {
            String name = textField.getText();
            if (name.equals("")) throw new MustBeNotEmptyException();
            setAcceptedValueTextFieldStyle(textField);
            controller.setLabWorkNameValidity(true);
            return true;
        } catch (Exception e) {
            setWrongValueTextFieldStyle(textField);
            controller.setLabWorkNameValidity(false);
            return false;
        }
    }


    public static boolean checkX(TextField textField, LabWorkAddAndUpdatingFormController controller) {
        try {
            float coordinates = Float.parseFloat(textField.getText());
            if (coordinates <= MINIMAL_X_COORDINATES || coordinates > 3000) throw new RangeException();
//            if (coordinates == Float.POSITIVE_INFINITY) throw new IllegalArgumentException();
            setAcceptedValueTextFieldStyle(textField);
            controller.setCoordinateXValidity(true);
            return true;
        } catch (Exception e) {
            setWrongValueTextFieldStyle(textField);
            controller.setCoordinateXValidity(false);
            return false;
        }

    }

    public static boolean checkX(TextField textField) {
        try {
            float coordinates = Float.parseFloat(textField.getText());
            if (coordinates <= MINIMAL_X_COORDINATES || coordinates > 3000f) throw new RangeException();
//            if (coordinates == Float.POSITIVE_INFINITY) throw new IllegalArgumentException();
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public static boolean checkY(TextField textField, LabWorkAddAndUpdatingFormController controller) {
        try {
            Long y = Long.parseLong(textField.getText());
            if (y > 3000) throw new RangeException();
            setAcceptedValueTextFieldStyle(textField);
            controller.setCoordinateYValidity(true);
            return true;
        } catch (Exception e) {
            setWrongValueTextFieldStyle(textField);
            controller.setCoordinateYValidity(false);
            return false;
        }
    }

    public static boolean checkY(TextField textField) {
        try {
            Long y = Long.parseLong(textField.getText());
            if (y > 3000) throw new RangeException();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean minimalPointAsk(TextField textField, LabWorkAddAndUpdatingFormController controller) {
        try {
            long minimalPoint = Long.parseLong(textField.getText());
            if (minimalPoint <= MINIMAL_POINT) throw new RangeException();
            setAcceptedValueTextFieldStyle(textField);
            controller.setMinimalPointAskValidity(true);
            return true;
        } catch (Exception e) {
            setWrongValueTextFieldStyle(textField);
            controller.setMinimalPointAskValidity(false);
            return false;
        }

    }

    public static boolean checkDifficulty(ComboBox comboBox, LabWorkAddAndUpdatingFormController controller) {
        setAcceptedValueTextFieldStyle(comboBox);
        controller.setDifficultyValidity(true);
        return true;
    }


    public static boolean nameDisciplineAsk(TextField textField, LabWorkAddAndUpdatingFormController controller) {
        try {
            String name = textField.getText();
            if (name.equals("")) throw new MustBeNotEmptyException();
            setAcceptedValueTextFieldStyle(textField);
            controller.setDisciplineNameValidity(true);
            return true;
        } catch (Exception e) {
            setWrongValueTextFieldStyle(textField);
            controller.setDisciplineNameValidity(false);
            return false;
        }
    }

    public static boolean practiceHoursDisciplineAsk(TextField textField, LabWorkAddAndUpdatingFormController controller) {
        try {
            int minimalPoint = Integer.parseInt(textField.getText());
            setAcceptedValueTextFieldStyle(textField);
            controller.setPracticeHoursAskValidity(true);
            return true;
        } catch (Exception e) {
            setWrongValueTextFieldStyle(textField);
            controller.setPracticeHoursAskValidity(false);
            return false;
        }
    }

    private static void setWrongValueTextFieldStyle(TextField textField) {
        textField.setStyle("""
                -fx-border-radius: 100;
                -fx-background-radius: 100;
                -fx-border-width: 2;
                -fx-border-color: red;
                """);
    }

    private static void setAcceptedValueTextFieldStyle(TextField textField) {
        textField.setStyle("""
                -fx-border-radius: 100;
                -fx-background-radius: 100;
                -fx-border-width: 2;
                -fx-border-color: green;
                """);
    }

    private static void setWrongValueTextFieldStyle(ComboBox comboBox) {
        comboBox.setStyle("""
                -fx-border-radius: 100;
                -fx-background-radius: 100;
                -fx-border-width: 2;
                -fx-border-color: red;
                """);
    }

    private static void setAcceptedValueTextFieldStyle(ComboBox comboBox) {
        comboBox.setStyle("""
                -fx-border-radius: 100;
                -fx-background-radius: 100;
                -fx-border-width: 2;
                -fx-border-color: green;
                """);
    }
}