package client.backend.tableHandlers;

import common.data.LabWork;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.TableView;


import java.util.List;
import java.util.NavigableSet;
import java.util.TreeSet;
import java.util.function.Predicate;

public class TableViewHandler {
    private TableView tableView;

    private ObservableList<LabWork> modelsCollection;

    private FilteredList<LabWork> filteredList;

    private SortedList<LabWork> sortedList;

    private static ObservableList<Predicate<LabWork>> predicates;

    public TableViewHandler(TableView tableView, ObservableList modelsCollection) {
        this.tableView = tableView;
        this.modelsCollection = modelsCollection;
        predicates = FXCollections.observableArrayList();
        predicates.addListener(this::listChanged);
        filteredList = new FilteredList(modelsCollection);
        filteredList.setPredicate(this::checkPredicates);
        sortedList = new SortedList(filteredList);
        sortedList.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.setItems(sortedList);
    }

    public void initializeColumns() {
        TableColumnsInitializer tableColumnsInitializer = new TableColumnsInitializer(tableView);
        tableColumnsInitializer.initializeColumns();
        listChanged(null);
    }
    public void initializeData(NavigableSet<LabWork> labs){
        Platform.runLater(()->modelsCollection.clear());
        Platform.runLater(()-> modelsCollection.addAll(labs));
    }
    private void listChanged(ListChangeListener.Change change) {
        filteredList.setPredicate(this::checkPredicates);
    }

    private boolean checkPredicates(Object o) {
        for (Predicate predicate : predicates) {
            if (!predicate.test(o)) {
                return false;
            }
        }
        return true;
    }

    public void addElement(LabWork labWork){
        Platform.runLater(()-> modelsCollection.add(labWork));
    }

    public void updateElement(LabWork newLabwork){
        if (modelsCollection.removeIf(oldMusicBand -> oldMusicBand.getId() == newLabwork.getId())){
            modelsCollection.add(newLabwork);
        }
    }

    public void removeElement(LabWork newLabwork){
        Platform.runLater(()-> modelsCollection.removeIf(labwork->labwork.getId() == newLabwork.getId()));
    }

    public static List<Predicate<LabWork>> getPredicates() {
        return predicates;
    }



}
