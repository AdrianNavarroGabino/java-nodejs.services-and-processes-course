module MessagesFX {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.google.gson;

    opens messagesfx;
    opens messagesfx.models;
    opens messagesfx.services;
    opens messagesfx.models.responses;
}