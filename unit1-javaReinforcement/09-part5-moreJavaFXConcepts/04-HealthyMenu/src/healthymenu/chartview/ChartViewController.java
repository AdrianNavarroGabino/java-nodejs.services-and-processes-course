// Adrián Navarro Gabino

package healthymenu.chartview;

import healthymenu.FXMLDocumentController;
import healthymenu.model.Food;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ChartViewController {

    @FXML
    private PieChart pieChart;
    @FXML
    private Button btnBack;

    public void initialize()
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/healthymenu/FXMLDocument.fxml"));

        try
        {
            Parent root = (Parent)loader.load();
        } catch (Exception e) {}
        FXMLDocumentController controller = (FXMLDocumentController)loader.getController();
        
        List<Food> food = controller.getFood();

        pieChart.getData().clear();

        Map<String, Integer> result;
        result = food.stream()
                .collect(Collectors.groupingBy(
                        f -> f.getCategory(),
                        Collectors.summingInt(f -> f.getWeight())
                ));
        result.forEach((cat, sum) -> {
            pieChart.getData().add(new PieChart.Data(cat, sum));
        });
    }

    @FXML
    private void back(ActionEvent event) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/healthymenu/FXMLDocument.fxml"));
        Parent view1 = loader.load();
        Scene view1Scene = new Scene(view1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
        stage.setScene(view1Scene);
        stage.show();
    }
    
}
