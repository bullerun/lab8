package client.UI.controllers;

import client.UI.resourcebundles.enums.AvailableLocales;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.Arrays;

public class LanguageChangeFormController {
    @FXML
    private ComboBox<AvailableLocales> languageComboBox;

    private Stage currentStage;

    @FXML
    public void initialize(){
        initLanguages();
    }

    private void initLanguages(){
        Arrays.stream(AvailableLocales.values()).forEach(languageComboBox.getItems()::add);
        languageComboBox.setValue(MainFormController.getCurrentLocale().get());
    }

    @FXML
    protected void onCancelButtonPressed(ActionEvent actionEvent){
        currentStage.close();
    }

    @FXML
    protected void onOkButtonPressed(ActionEvent actionEvent){
        MainFormController.setCurrentLocale(languageComboBox.getValue());
        currentStage.close();
    }
    public void setCurrentStage(Stage stage){
        this.currentStage = stage;
    }
}
