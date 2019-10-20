module FlightsFX {
    requires javafx.fxml;
    requires javafx.controls;

    opens flightsfx;
    opens flightsfx.model;
    opens flightsfx.chartview;
}