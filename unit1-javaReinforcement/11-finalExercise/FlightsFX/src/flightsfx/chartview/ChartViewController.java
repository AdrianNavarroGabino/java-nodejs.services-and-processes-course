package flightsfx.chartview;

import flightsfx.FXMLMainViewController;
import flightsfx.model.Flight;
import flightsfx.utils.FileUtils;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.*;

/**
 * <h1>Chart View Controller</h1>
 * Class to manage the pie chart with the number of flights per destination.
 * @author Adrián Navarro Gabino
 * @version 1.0
 */
public class ChartViewController {
    @FXML
    private PieChart pieChart;

    private List<Flight> flights;

    /**
     * Initializes the FXML view, gets the flights information a creates the
     * pie chart.
     */
    public void initialize(){
        // Gets the flights list from the main view.
        FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("/flightsfx/FXMLMainView.fxml"));
        FXMLMainViewController controller = loader.getController();
        flights = controller.getFlights();

        pieChart.getData().clear();
        Map<String, Integer> result = new HashMap<>();
        for(Flight f: flights){
            String i = f.getDestination();
            Integer j = result.get(i);
            result.put(i, (j == null) ? 1 : j + 1);
        }

        result.forEach((dest, number) -> {
            pieChart.getData().add(new PieChart.Data(dest, number));
        });
    }

    /**
     * Backs to the main view by clicking the back button.
     * @param event Action event
     * @throws IOException Input/Output Exception
     */
    @FXML
    void back(ActionEvent event) throws IOException {
        FileUtils.loadScreen("/flightsfx/FXMLMainView.fxml",
                (Stage) ((Node) event.getSource())
                        .getScene().getWindow());
    }
}
