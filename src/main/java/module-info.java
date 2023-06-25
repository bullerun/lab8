module client.backend {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.sql;
    requires org.postgresql.jdbc;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;



    opens client to javafx.fxml;
    exports client;
    exports client.UI.controllers;
    opens client.UI.controllers to javafx.fxml;


}