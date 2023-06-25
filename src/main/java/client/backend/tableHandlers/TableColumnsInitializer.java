package client.backend.tableHandlers;

import client.UI.controllers.MainFormController;
import common.data.Difficulty;
import common.data.LabWork;
import javafx.beans.property.*;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
//import org.controlsfx.control.Notifications;
//import org.controlsfx.control.table.TableFilter;
//import shared.core.models.Country;
//import shared.core.models.MusicBand;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoUnit;

public class TableColumnsInitializer {
    private TableView tableView;

    public TableColumnsInitializer(TableView tableView) {
        this.tableView = tableView;
    }

    public void initializeColumns() {
        TableColumn<LabWork, Long> idColumn = new FixedNameTableColumn<>(ColumnNames.ID_COLUMN);
        idColumn.setCellValueFactory(cellValue -> new SimpleLongProperty(cellValue.getValue().getId()).asObject());
        TableColumn<LabWork, String> nameColumn = new FixedNameTableColumn<>(ColumnNames.NAME_COLUMN);
        nameColumn.setCellValueFactory(cellValue -> new SimpleStringProperty(cellValue.getValue().getName()));
        TableColumn<LabWork, Long> ownerIdColumn = new FixedNameTableColumn<>(ColumnNames.OWNER_ID_COLUMN);
        ownerIdColumn.setCellValueFactory(cellValue -> new SimpleLongProperty(cellValue.getValue().getOwnerID()).asObject());
        TableColumn<LabWork, Float> coordinateXColumn = new FixedNameTableColumn<>(ColumnNames.COORDINATES_X_COLUMN);
        coordinateXColumn.setCellValueFactory(cellValue -> new SimpleFloatProperty(cellValue.getValue().getCoordinates().getX()).asObject());
        TableColumn<LabWork, Long> coordinateYColumn = new FixedNameTableColumn<>(ColumnNames.COORDINATES_Y_COLUMN);
        coordinateYColumn.setCellValueFactory(cellValue -> new SimpleLongProperty(cellValue.getValue().getCoordinates().getY()).asObject());
        TableColumn<LabWork, LocalDate> creationDateColumn = new FixedNameTableColumn<>(ColumnNames.CREATION_DATE_COLUMN);
        creationDateColumn.setCellValueFactory(cellValue -> new SimpleObjectProperty<>(cellValue.getValue().getCreationDate()));
        TableColumn<LabWork, Long> minimalPoint = new FixedNameTableColumn<>(ColumnNames.MINIMAL_POINT);
        minimalPoint.setCellValueFactory(cellValue -> new SimpleLongProperty(cellValue.getValue().getMinimalPoint()).asObject());

        TableColumn<LabWork, Difficulty> difficulty = new FixedNameTableColumn<>(ColumnNames.DIFFICULT);
        difficulty.setCellValueFactory(cellValue -> new SimpleObjectProperty(cellValue.getValue().getDifficulty()));


        TableColumn<LabWork, String> disciplineName = new FixedNameTableColumn<>(ColumnNames.DISCIPLINE_NAME);
        disciplineName.setCellValueFactory(cellValue -> new SimpleStringProperty(cellValue.getValue().getDiscipline().getName()));
        TableColumn<LabWork, Long> practiceHours = new FixedNameTableColumn<>(ColumnNames.PRACTICE_HOURS);
        practiceHours.setCellValueFactory(cellValue -> new SimpleLongProperty(cellValue.getValue().getDiscipline().getPracticeHours()).asObject());


        tableView.getColumns().setAll(idColumn, nameColumn, ownerIdColumn, coordinateXColumn, coordinateYColumn, creationDateColumn, minimalPoint, difficulty, disciplineName, practiceHours);
    }

}
