// Adrián Navarro Gabino

package flightsfx.chartview;

import flightsfx.FXMLMainViewController;
import flightsfx.model.Flight;
import flightsfx.utils.FileUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChartViewController {
    @FXML
    private PieChart pieChart;

    @FXML
    private Button backBtn;

    private List<Flight> flights;

    public void initialize(){
        FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("/flightsfx/FXMLMainView.fxml"));

        try{
            Parent root = (Parent)loader.load();
        } catch (IOException e) { }

        FXMLMainViewController controller =
                (FXMLMainViewController)loader.getController();

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

    @FXML
    void back(ActionEvent event) throws IOException {
        FileUtils.loadScreen("/flightsfx/FXMLMainView.fxml",
                (Stage) ((Node) event.getSource())
                        .getScene().getWindow());
    }
}
