module healthyMenu {

    requires javafx.controls;
    requires javafx.fxml;

    exports healthymenu.model;

    opens healthymenu;
    opens healthymenu.chartview;
}
