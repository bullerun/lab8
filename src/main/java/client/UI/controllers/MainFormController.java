package client.UI.controllers;


import client.Main;
import client.UI.resourcebundles.enums.AvailableLocales;
import client.UI.resourcebundles.enums.LabWorkAddAndUpdatingFormElements;
import client.UI.resourcebundles.enums.MainFormElements;
import client.backend.Console;
import client.backend.LabAsk;
import client.backend.tableHandlers.TableViewHandler;
import common.DataField;
import common.data.LabWork;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;
//import org.controlsfx.control.Notifications;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.NavigableSet;

public class MainFormController {
    @FXML
    private Menu infoMenu;

    @FXML
    private Menu settingsMenu;

    private Scene primaryScene;
    private volatile TableViewHandler tableViewHandler;

    @FXML
    private MenuItem languageMenuItem;

    @FXML
    private MenuItem infoMenuItem;

    @FXML
    private MenuItem logOutMenuItem;

    @FXML
    private HBox filtersHBox;

    @FXML
    private TableView tableView;


    @FXML
    private Button removeFiltersButton;

    @FXML
    private Button executeScriptButton;

    @FXML
    protected Button createFilterButton;

    @FXML
    protected Button addButton;


    @FXML
    protected Button updateButton;

    @FXML
    protected Button removeButton;

    @FXML
    protected Button removeByIdButton;


    @FXML
    private Button removeGreater;

    @FXML
    private Button removeLower;
    @FXML
    protected Button clearButton;


    @FXML
    protected Label controllersLabel;

    @FXML
    private Menu userMenu;

    @FXML
    private Button visualizeButton;

    private static SimpleObjectProperty<AvailableLocales> currentLocale = new SimpleObjectProperty<>(AvailableLocales.ENGLISH);

    private static ObservableList<LabWork> modelsCollection = FXCollections.observableArrayList();

    private static MainFormController mainFormController;


    @FXML
    public void initialize() {
        mainFormController = this;
        tableViewHandler = new TableViewHandler(tableView, modelsCollection);
        tableViewHandler.initializeColumns();
        currentLocale.addListener(change -> updateLocale());
        updateLocale();
        System.out.println(1);
        Console.startServerListener();
        System.out.println(4);
    }

    private void updateLocale() {
        tableViewHandler.initializeColumns();

        removeFiltersButton.setText(MainFormElements.REMOVE_FILTERS_BUTTON.toString());
        createFilterButton.setText(MainFormElements.CREATE_FILTER_BUTTON.toString());
        addButton.setText(MainFormElements.ADD_BUTTON.toString());
        removeLower.setText(MainFormElements.REMOVE_LOWER_BUTTON.toString());
        removeGreater.setText(MainFormElements.REMOVE_GREATER_BUTTON.toString());
        updateButton.setText(MainFormElements.UPDATE_BUTTON.toString());
        removeButton.setText(MainFormElements.REMOVE_BUTTON.toString());
        removeByIdButton.setText(MainFormElements.REMOVE_BY_ID_BUTTON.toString());

        clearButton.setText(MainFormElements.CLEAR_BUTTON.toString());

        controllersLabel.setText(MainFormElements.CONTROLLERS_LABEL.toString());
        infoMenu.setText(MainFormElements.INFO_MENU.toString());
        settingsMenu.setText(MainFormElements.SETTINGS_MENU.toString());
        logOutMenuItem.setText(MainFormElements.LOG_OUT_MENU_ITEM.toString());
        languageMenuItem.setText(MainFormElements.LANGUAGE_MENU_ITEM.toString());
        executeScriptButton.setText(MainFormElements.EXECUTE_SCRIPT_BUTTON.toString());
        visualizeButton.setText(MainFormElements.VISUALIZE_BUTTON.toString());
        infoMenuItem.setText(MainFormElements.INFO_MENU.toString());
    }

    @FXML
    protected void onAddButtonPressed(ActionEvent actionEvent) {
        Button button = (Button) actionEvent.getSource();
        try {
            button.setDisable(true);
            LabWorkAddAndUpdatingFormController lab = initCreatingForm();
            LabWork data = lab.getData();
            if (data == null) return;
            Console.add(data);
        } catch (IOException exception) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setContentText(RuntimeOutputs.CAN_NOT_INIT_COMPONENT.toString());
            alert.show();
        } finally {
            button.setDisable(false);
        }
    }

    @FXML
    protected void onClearButtonPressed(ActionEvent actionEvent) {
        Console.selectCommand(new String[]{"clear", ""});
    }

    @FXML
    protected void onFilterCreatingButtonPressed(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("FilterCreatorForm.fxml"));
            Parent node = fxmlLoader.load();
            FilterCreatorFormController filterCreatorFormController = fxmlLoader.getController();
            Scene scene = new Scene(node, 400, 300);
            Stage stage = new Stage();
            stage.setResizable(false);
            filterCreatorFormController.setCurrentStage(stage);
            stage.setScene(scene);
            stage.show();
        } catch (IOException exception) {

        }
    }

    @FXML
    protected void onFilterRemovingButtonPressed(ActionEvent actionEvent) {
        filtersHBox.getChildren().clear();
    }

    @FXML
    protected void onRemoveByIdButtonPressed(ActionEvent actionEvent) {
        Button button = (Button) actionEvent.getSource();
        try {
            button.setDisable(true);
            AdditionalFormOfDataCollectionController additionalFormOfDataCollectionController = initAdditionalForm();
            String value = additionalFormOfDataCollectionController.getResult();
            prepareAndInvokeRemoveByIdCommand(value);
        } catch (IOException exception) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setContentText(RuntimeOutputs.CAN_NOT_INIT_COMPONENT.toString());
            alert.show();
        } finally {
            button.setDisable(false);
        }
    }


    @FXML
    void onRemoveGreater(ActionEvent actionEvent) {
        Button button = (Button) actionEvent.getSource();
        try {
            button.setDisable(true);
            AdditionalFormOfDataCollectionController additionalFormOfDataCollectionController = initAdditionalForm();
            String value = additionalFormOfDataCollectionController.getResult();
            prepareAndInvokeRemoveGreater(value);
        } catch (IOException exception) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setContentText(RuntimeOutputs.CAN_NOT_INIT_COMPONENT.toString());
            alert.show();
        } finally {
            button.setDisable(false);
        }
    }

    @FXML
    protected void onRemoveButtonPressed(ActionEvent actionEvent) {
        Button button = (Button) actionEvent.getSource();
        try {
            button.setDisable(true);
            LabWork labWork = (LabWork) tableView.getSelectionModel().getSelectedItem();
            if (labWork != null) {
                if (!checkModelUserId(labWork)) return;
                Console.selectCommand(new String[]{"remove_by_id", String.valueOf(labWork.getId())});
            }
        } finally {
            button.setDisable(false);
        }

    }

    @FXML
    void onRemoveLower(ActionEvent actionEvent) {
        Button button = (Button) actionEvent.getSource();
        try {
            button.setDisable(true);
            AdditionalFormOfDataCollectionController additionalFormOfDataCollectionController = initAdditionalForm();
            String value = additionalFormOfDataCollectionController.getResult();
            prepareAndInvokeRemoveLower(value);
        } catch (IOException exception) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setContentText(RuntimeOutputs.CAN_NOT_INIT_COMPONENT.toString());
            alert.show();
        } finally {
            button.setDisable(false);
        }
    }

    private boolean checkModelUserId(LabWork labWork) {
        return Console.client.getId().equals(labWork.getOwnerID());
    }

    @FXML
    protected void onExecuteScriptButtonPressed(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null && file.exists()) {
            Console.execute_script(file.getAbsolutePath());
        }
        Console.selectCommand(new String[]{"show", ""});
    }

    @FXML
    protected void onVisualizationButtonPressed(ActionEvent actionEvent) {

    }

    @FXML
    protected void onLogOutPressed(ActionEvent actionEvent) {

    }
    @FXML
    protected void onUpdateButtonPressed(ActionEvent actionEvent) {
        Button button = (Button) actionEvent.getSource();
        try {
            LabWork labWork = (LabWork) tableView.getSelectionModel().getSelectedItem();
            if (labWork != null) {
                try {
                    if (!checkModelUserId(labWork)) return;
                    LabWorkAddAndUpdatingFormController labWorkAddAndUpdatingFormController = initUpdatingForm(labWork);
                    LabWork data = labWorkAddAndUpdatingFormController.getData();
                    if (data == null) return;
                    data.setId(labWork.getId());
                    data.setOwnerID(Console.client.getId());
                    Console.update(data);
                } catch (IOException ignored) {

                }
            }
        } finally {
            button.setDisable(false);
        }
    }
    @FXML
    protected void onLanguageMenuItemPressed(ActionEvent actionEvent) {
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
    protected void onInfoMenuItemPressed(ActionEvent actionEvent) {
        Console.selectCommand(new String[]{"info", ""});
    }


    public synchronized static MainFormController getMainFormController() {
        return mainFormController;
    }

    public HBox getFiltersHBox() {
        return filtersHBox;
    }

    public TableView getTableView() {
        return tableView;
    }

    public Button getRemoveFiltersButton() {
        return removeFiltersButton;
    }

    public synchronized TableViewHandler getTableViewHandler() {
        return tableViewHandler;
    }

    public Menu getUserMenu() {
        return userMenu;
    }

    public static SimpleObjectProperty<AvailableLocales> getCurrentLocale() {
        return currentLocale;
    }

    public static void setCurrentLocale(AvailableLocales availableLocales) {
        currentLocale.set(availableLocales);
    }

    public void setPrimaryScene(Scene scene) {
        this.primaryScene = scene;
    }

    public Scene getPrimaryScene() {
        return primaryScene;
    }

    private AdditionalFormOfDataCollectionController initAdditionalForm() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("AdditionalFormOfDataCollection.fxml"));
        Parent node = fxmlLoader.load();
        AdditionalFormOfDataCollectionController additionalFormOfDataCollectionController = fxmlLoader.getController();
        Scene scene = new Scene(node, 300, 200);
        Stage stage = new Stage();
        additionalFormOfDataCollectionController.setCurrentStage(stage);
        stage.setScene(scene);
        stage.showAndWait();
        return additionalFormOfDataCollectionController;
    }

    private void prepareAndInvokeRemoveByIdCommand(String value) {
        Console.selectCommand(new String[]{"remove_by_id", value});
    }

    private void prepareAndInvokeRemoveGreater(String value) {
        Console.selectCommand(new String[]{"remove_greater", value});
    }
    private void prepareAndInvokeRemoveLower(String value) {
        Console.selectCommand(new String[]{"remove_lower", value});
    }
    private LabWorkAddAndUpdatingFormController initCreatingForm() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("LabworkAddAndUpdateForm.fxml"));
        Parent node = fxmlLoader.load();
        Scene scene = new Scene(node, 400, 800);
        Stage stage = new Stage();
        LabWorkAddAndUpdatingFormController labWorkAddAndUpdatingFormController = fxmlLoader.getController();
        labWorkAddAndUpdatingFormController.setCurrentStage(stage);
        stage.setScene(scene);
        stage.showAndWait();
        return labWorkAddAndUpdatingFormController;
    }
    private LabWorkAddAndUpdatingFormController initUpdatingForm(LabWork labWork) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("LabworkAddAndUpdateForm.fxml"));
        Parent node = fxmlLoader.load();
        Scene scene = new Scene(node, 400, 800);
        Stage stage = new Stage();
        LabWorkAddAndUpdatingFormController labWorkAddAndUpdatingFormController = fxmlLoader.getController();
        labWorkAddAndUpdatingFormController.setCurrentStage(stage);
        labWorkAddAndUpdatingFormController.setData(labWork);
        stage.setScene(scene);
        stage.showAndWait();
        return labWorkAddAndUpdatingFormController;
    }
}