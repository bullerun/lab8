package client.UI.controllers;

import client.Main;
import client.UI.resourcebundles.enums.AuthorizationFormElements;
import client.backend.Console;
import common.ResponseWithBooleanType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AuthorizationFormController {

    @FXML
    private Label authorizationLabel;

    @FXML
    private Label passwordLable;

    @FXML
    private Button signInButton;

    @FXML
    private Button signUpButton;

    @FXML
    private MenuItem languageMenuItem;

    @FXML
    private TextField logInTextField;

    @FXML
    private Label loginLabel;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private Menu settingsMenu;
    @FXML
    private Label textLabel;

    @FXML
    void onLanguageButtonDown(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("LanguageChangeForm.fxml"));
            Parent parent = fxmlLoader.load();
            LanguageChangeFormController languageChangingFormController = fxmlLoader.getController();
            Scene scene = new Scene(parent, 300, 400);
            Stage stage = new Stage();
            languageChangingFormController.setCurrentStage(stage);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ignored) {

        }
    }

    @FXML
    void onSignInButton(ActionEvent event) {
        Button button = (Button) event.getTarget();
        button.setDisable(true);
        ResponseWithBooleanType response = Console.authentication("login", logInTextField.getText(), passwordTextField.getText());
        if (response != null) {
            if (response.getAuth()) {
                launchGRid();
            } else {
                textLabel.setText(response.getResponse().toString());
            }
        }
        button.setDisable(false);
    }


    @FXML
    void onSignUpButton(ActionEvent event) {
        Button button = (Button) event.getTarget();
        button.setDisable(true);
        ResponseWithBooleanType response = Console.authentication("reg", logInTextField.getText(), passwordTextField.getText());
        if (response != null) {
            if (response.getAuth()) {
                launchGRid();
            } else {
                textLabel.setText(response.getResponse().toString());
            }
        }
        button.setDisable(false);
    }

    private void launchGRid() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("DataGridForm.fxml"));
            Parent parent = fxmlLoader.load();

            MainFormController mainFormController = fxmlLoader.getController();
            mainFormController.getUserMenu().setText("ID = " + Console.client.getId().toString());
            Scene scene = new Scene(parent, 800, 600);
            scene.getStylesheets().add(Main.class.getResource("style.css").toExternalForm());
            mainFormController.setPrimaryScene(scene);
            Main.getPrimaryStage().setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void initialize() {
        MainFormController.getCurrentLocale().addListener(change -> updateLocale());
        updateLocale();
    }

    private void updateLocale() {
        authorizationLabel.setText(AuthorizationFormElements.AUTHORIZATION.toString());
        logInTextField.setPromptText(AuthorizationFormElements.LOG_IN_TEXT_FIELD.toString());
        passwordTextField.setPromptText(AuthorizationFormElements.PASSWORD_TEXT_FIELD.toString());
        settingsMenu.setText(AuthorizationFormElements.SETTINGS_MENU.toString());
        languageMenuItem.setText(AuthorizationFormElements.LANGUAGE_MENU_ITEM.toString());
        loginLabel.setText(AuthorizationFormElements.LOGIN_LABEL.toString());
        passwordLable.setText(AuthorizationFormElements.PASSWORD_LABEL.toString());
        signUpButton.setText(AuthorizationFormElements.SIGN_UP_BUTTON.toString());
        signInButton.setText(AuthorizationFormElements.SIGN_IN_BUTTON.toString());
    }
}
