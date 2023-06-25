package client.UI.controllers;

import client.backend.tableHandlers.ColumnNames;
import client.backend.tableHandlers.TableViewHandler;
import client.backend.tableHandlers.predicatefactory.FilterSigns;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.util.function.Predicate;

public class FilterFormController {
    @FXML
    private GridPane filterFormMainPane;
    @FXML
    private Label columnForFilteringLabel;
    @FXML
    private Label filterSignLabel;
    @FXML
    private Label filteringValueLabel;

    private ColumnNames columnForFiltering;

    private Predicate predicate;

    private EventHandler removeButtonPressedEventHandler;

    @FXML
    public void initialize() {
        removeButtonPressedEventHandler = this::removeListeners;
        MainFormController.getMainFormController().getRemoveFiltersButton().addEventHandler(MouseEvent.MOUSE_CLICKED, removeButtonPressedEventHandler);
        MainFormController.getCurrentLocale().addListener(change->updateLocale());
    }

    private void updateLocale(){
        columnForFilteringLabel.setText(columnForFiltering.toString());
    }

    @FXML
    private void onCloseButtonPressed(ActionEvent actionEvent) {
        MainFormController mainFormController = MainFormController.getMainFormController();
        mainFormController.getFiltersHBox().getChildren().remove(filterFormMainPane);
        removeListeners(null);
    }

    private void removeListeners(Event event) {
        MainFormController.getMainFormController().getRemoveFiltersButton().removeEventHandler(MouseEvent.MOUSE_CLICKED, removeButtonPressedEventHandler);
        TableViewHandler.getPredicates().remove(predicate);
    }

    public void setColumnForFilteringLabel(ColumnNames value) {
        columnForFiltering = value;
        columnForFilteringLabel.setText(value.toString());
    }

    public void setFilterSignLabel(FilterSigns value) {
        filterSignLabel.setText(value.toString());
    }

    public void setFilteringValueLabel(String value) {
        filteringValueLabel.setText(value);
    }

    public void setPredicate(Predicate predicate) {
        this.predicate = predicate;
    }

}
