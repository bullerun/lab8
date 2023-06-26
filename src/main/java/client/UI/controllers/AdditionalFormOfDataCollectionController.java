package client.UI.controllers;

import client.UI.resourcebundles.enums.LabWorkAddAndUpdatingFormElements;
//import client.UI.resourcebundles.enums.RuntimeOutputs;
import client.UI.resourcebundles.enums.RuntimeOutputs;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

public class AdditionalFormOfDataCollectionController {
    @FXML
    private TextField valueTextField;

    @FXML
    private Button cancelButton;

    private Stage currentStage;

    @FXML
    public void initialize(){
        MainFormController.getCurrentLocale().addListener(change->updateLocale());
        updateLocale();
    }

    private void updateLocale(){
        cancelButton.setText(LabWorkAddAndUpdatingFormElements.CANCEL_BUTTON.toString());
    }

    @FXML
    protected void onOkButtonPressed(ActionEvent actionEvent){
        if (valueTextField.getText().isBlank()){
            Notifications.create().position(Pos.TOP_CENTER).text(RuntimeOutputs.VALUE_WAS_NOT_ENTERED.toString()).show();
        }
        currentStage.close();
    }



    @FXML
    protected void onCancelButtonPressed(ActionEvent event) {
        valueTextField.setText("");
        currentStage.close();
    }



    public void setCurrentStage(Stage currentStage){
        this.currentStage = currentStage;
    }

    public void setPromptText(String promptText){
        valueTextField.setPromptText(promptText);
    }

    public String getResult(){
        return valueTextField.getText();
    }
}
