package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private BarChart<String, Number> chart;

    String[] categories = {"Jan", "Feb", "Mar", "Apr",
                            "May", "Jun", "Jul", "Aug",
                            "Sep", "Oct", "Nov", "Dec"};
    double[] temperatures = {9, 8, 14, 17, 21, 24, 28, 30, 26, 21, 16, 11};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        chart.setTitle("Temperature average");
        XYChart.Series data = new XYChart.Series();
        data.setName("2017");

        for(int i = 0 ; i < categories.length; i++) {
            data.getData().add(new XYChart.Data(categories[i], temperatures[i]));
        }

        chart.getData().add(data);
    }
}
