package client.UI.controllers;

import client.UI.resourcebundles.enums.LabWorkAddAndUpdatingFormElements;
import client.backend.LabAskStatic;
import common.DataField;
import common.data.Coordinates;
import common.data.Difficulty;
import common.data.Discipline;
import common.data.LabWork;
import common.exception.MustBeNotEmptyException;
import common.exception.RangeException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class LabWorkAddAndUpdatingFormController {
    private Stage currentStage;
    private LabWork data;
    private boolean isLabWorkNameValidity = false;
    private boolean isXCoordinatesValidity = false;
    private boolean isYCoordinatesValidity = false;
    private boolean isMinimalPointValidation = false;
    private boolean isDifficultValidation = true;
    private boolean isDisciplineNameValidation = false;
    private boolean isPracticeHoursValidation = false;

    @FXML
    private Button cancelButton;

    @FXML
    private Label coordinateXLabel;

    @FXML
    private TextField coordinateXTextField;

    @FXML
    private Label coordinateYLabel;

    @FXML
    private TextField coordinateYTextField;

    @FXML
    private ComboBox<Difficulty> difficultyComboBox;

    @FXML
    private Label difficultyLabel;

    @FXML
    private Label disciplineNameLabel;

    @FXML
    private TextField disciplineNameTextField;

    @FXML
    private Label labWorkNameLabel;

    @FXML
    private TextField labWorkNameTextField;

    @FXML
    private Label minimalPointLabel;

    @FXML
    private TextField minimalPointTextField;

    @FXML
    private Button okButton;

    @FXML
    private Label practiceHoursLabel;

    @FXML
    private TextField practiceHoursTextField;

    @FXML
    void onButtonMouseEntered(MouseEvent event) {

    }

    @FXML
    void onButtonMouseExited(MouseEvent event) {

    }

    @FXML
    void onCloseButtonPressed(ActionEvent event) {
        currentStage.close();
    }

    @FXML
    void onOkButtonPressed(ActionEvent event) {
        if (checkValidation()) {
            data = collectData();
            currentStage.close();
        }
    }

    private boolean checkValidation() {
        return isLabWorkNameValidity & isXCoordinatesValidity & isYCoordinatesValidity & isMinimalPointValidation & isDifficultValidation & isDisciplineNameValidation & isPracticeHoursValidation;
    }

    private LabWork collectData() {
        LabWork data = new LabWork();
        try {
            data.setName(labWorkNameTextField.getText());
        } catch (MustBeNotEmptyException e) {
            return null;
        }
        float coordinateX = Float.parseFloat(coordinateXTextField.getText());
        long coordinateY = Long.parseLong(coordinateYTextField.getText().replace(",", "."));
        Coordinates coordinates = new Coordinates();
        try {
            coordinates.setX(coordinateX);
        } catch (RangeException e) {
            return null;
        }
        coordinates.setY(coordinateY);
        data.setCoordinates(coordinates);
        try {
            data.setMinimalPoint(Long.parseLong(minimalPointTextField.getText()));
        } catch (RangeException e) {
            return null;
        }
        Difficulty difficulty = difficultyComboBox.getValue();
        if (difficulty != null) {
            data.setDifficulty(difficulty.toString());
        }


        String disciplineName = disciplineNameTextField.getText();
        Integer practiceHours = practiceHoursTextField.getText().isEmpty() ? null : Integer.parseInt(practiceHoursTextField.getText());
        if (practiceHours != null) {
            Discipline discipline = new Discipline(disciplineName, practiceHours);
            data.setDiscipline(discipline);
        }
        return data;
    }

    public void setCurrentStage(Stage stage) {
        this.currentStage = stage;
    }

    public LabWork getData() {
        return data;
    }

    @FXML
    public void initialize() {
        initListeners();
        preValidation();
        initDifficultyComboBox();
        updateLocale();
        MainFormController.getCurrentLocale().addListener(change -> updateLocale());

    }

    private void preValidation() {
        LabAskStatic.checkName(labWorkNameTextField, this);
        LabAskStatic.checkX(coordinateXTextField, this);
        LabAskStatic.checkY(coordinateYTextField, this);
        LabAskStatic.minimalPointAsk(minimalPointTextField, this);
        LabAskStatic.checkDifficulty(difficultyComboBox, this);
        LabAskStatic.nameDisciplineAsk(disciplineNameTextField, this);
        LabAskStatic.practiceHoursDisciplineAsk(practiceHoursTextField, this);
    }

    private void initListeners() {
        labWorkNameTextField.textProperty().addListener(change -> LabAskStatic.checkName(labWorkNameTextField, this));
        coordinateXTextField.textProperty().addListener(change -> LabAskStatic.checkX(coordinateXTextField, this));
        coordinateYTextField.textProperty().addListener(change -> LabAskStatic.checkY(coordinateYTextField, this));
        minimalPointTextField.textProperty().addListener(change -> LabAskStatic.minimalPointAsk(minimalPointTextField, this));
        difficultyComboBox.valueProperty().addListener(change -> LabAskStatic.checkDifficulty(difficultyComboBox, this));
        disciplineNameTextField.textProperty().addListener(change -> LabAskStatic.nameDisciplineAsk(disciplineNameTextField, this));
        practiceHoursTextField.textProperty().addListener(change -> LabAskStatic.practiceHoursDisciplineAsk(practiceHoursTextField, this));

    }

    private void initDifficultyComboBox() {

        for (Difficulty genre : Difficulty.values()) {
            difficultyComboBox.getItems().add(genre);
        }

    }

    private void updateLocale() {
        labWorkNameTextField.setPromptText(LabWorkAddAndUpdatingFormElements.LAB_WORK_NAME_TEXT_FIELD.toString());
        coordinateXTextField.setPromptText(LabWorkAddAndUpdatingFormElements.COORDINATE_X_TEXT_FIELD.toString());
        coordinateYTextField.setPromptText(LabWorkAddAndUpdatingFormElements.COORDINATE_Y_TEXT_FIELD.toString());
        minimalPointTextField.setPromptText(LabWorkAddAndUpdatingFormElements.MINIMAL_POINT.toString());
        difficultyComboBox.setPromptText(LabWorkAddAndUpdatingFormElements.DIFFICULTY_COMBO_BOX.toString());
        disciplineNameTextField.setPromptText(LabWorkAddAndUpdatingFormElements.DISCIPLINE_NAME_TEXT_FIELD.toString());
        practiceHoursTextField.setPromptText(LabWorkAddAndUpdatingFormElements.PRACTICE_HOURS_TEXT_FIELD.toString());


        labWorkNameLabel.setText(LabWorkAddAndUpdatingFormElements.LAB_WORK_NAME_LABEL.toString());
        coordinateXLabel.setText(LabWorkAddAndUpdatingFormElements.COORDINATE_X_LABEL.toString());
        coordinateYLabel.setText(LabWorkAddAndUpdatingFormElements.COORDINATE_Y_LABEL.toString());
        minimalPointLabel.setText(LabWorkAddAndUpdatingFormElements.MINIMAL_POINT_LABEL.toString());
        difficultyLabel.setText(LabWorkAddAndUpdatingFormElements.DIFFICULTY_LABEL.toString());
        disciplineNameLabel.setText(LabWorkAddAndUpdatingFormElements.DISCIPLINE_NAME_LABEL.toString());
        practiceHoursLabel.setText(LabWorkAddAndUpdatingFormElements.PRACTICE_HOURS_LABEL.toString());

        okButton.setText(LabWorkAddAndUpdatingFormElements.OK_Button.toString());
        cancelButton.setText(LabWorkAddAndUpdatingFormElements.CANCEL_BUTTON.toString());
    }

    public void setLabWorkNameValidity(boolean b) {
        isLabWorkNameValidity = b;
    }

    public void setCoordinateXValidity(boolean b) {
        isXCoordinatesValidity = b;
    }

    public void setCoordinateYValidity(boolean b) {
        isYCoordinatesValidity = b;
    }

    public void setMinimalPointAskValidity(boolean b) {
        isMinimalPointValidation = b;
    }

    public void setDifficultyValidity(boolean b) {
        isDifficultValidation = b;
    }

    public void setDisciplineNameValidity(boolean b) {
        isDisciplineNameValidation = b;
    }

    public void setPracticeHoursAskValidity(boolean b) {
        isPracticeHoursValidation = b;
    }

    public void setData(LabWork labWork) {
        labWorkNameTextField.setText(labWork.getName());
        coordinateXTextField.setText(String.valueOf(labWork.getCoordinates().getX()));
        coordinateYTextField.setText(String.valueOf(labWork.getCoordinates().getY()));
        minimalPointTextField.setText(String.valueOf(labWork.getMinimalPoint()));
        if (labWork.getDifficulty()!=null) difficultyLabel.setText(labWork.getDifficulty().toString());
        disciplineNameTextField.setText(labWork.getDiscipline().getName());
        practiceHoursTextField.setText(String.valueOf(labWork.getDiscipline().getPracticeHours()));
    }
}
