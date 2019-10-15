// Adrián Navarro Gabino

package healthymenu;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import healthymenu.model.Food;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class FXMLDocumentController  {
    
    private Label label;
    @FXML
    private TableView<Food> tableFood;
    @FXML
    private TableColumn<Food, String> colFoodName;
    @FXML
    private TableColumn<Food, String> colFoodCategory;
    @FXML
    private TableColumn<Food, Integer> colWeightG;
    @FXML
    private TableColumn<Food, Float> colWeightOz;
    @FXML
    private TextField txtName;
    @FXML
    private ChoiceBox<String> choiceCategory;
    @FXML
    private TextField txtWeight;
    @FXML
    private Button btnAdd;
    
    ObservableList<Food> food;
    @FXML
    private Button btnChart;
    @FXML
    private Button btnDelete;

    public void initialize()
    {
        choiceCategory.setItems(FXCollections.observableArrayList("Fruits and vegetables", "Meat and fish", "Milk derivatives", "Other"));
        
        food = FXCollections.observableArrayList(readFile());
        
        colFoodName.setCellValueFactory(new PropertyValueFactory("name"));
        colFoodCategory.setCellValueFactory(new PropertyValueFactory("category"));
        colWeightG.setCellValueFactory(new PropertyValueFactory("weight"));
        colWeightOz.setCellValueFactory(new PropertyValueFactory("weightInOz"));
                
        saveFile(food);
        
        tableFood.setItems(food);
    }    
    
    private static List<Food> readFile()
    {
        try
        {
            return Files.lines(Paths.get("food.txt"))
                    .map(line -> new Food(line.split(";")[0], line.split(";")[1], Integer.parseInt(line.split(";")[2])))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    private static void saveFile(List<Food> food)
    {
        try(PrintWriter pw = new PrintWriter("food.txt"))
        {
            food.stream()
                .forEach(f -> pw.println(f.toString()));
        } catch (Exception e) {}
    }

    @FXML
    private void addFood(ActionEvent event) 
    {
        if (txtName.getText().equals("") || choiceCategory.getSelectionModel().getSelectedIndex() < 0 || txtWeight.getText().equals(""))
        {
            Alert dialog = new Alert(AlertType.ERROR);
            dialog.setTitle("Error");
            dialog.setHeaderText("Error adding data");
            dialog.setContentText("No field can be empty");
            dialog.showAndWait();            
        } else {
            food.add(new Food(txtName.getText(), choiceCategory.getSelectionModel().getSelectedItem(), Integer.parseInt(txtWeight.getText())));
            saveFile(food);
        }
    }

    @FXML
    private void goToChartView(ActionEvent event) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/healthymenu/chartview/ChartView.fxml"));
        Parent view1 = loader.load();
        Scene view1Scene = new Scene(view1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
        stage.setScene(view1Scene);
        stage.show();        
    }
    
    public List<Food> getFood()
    {
        return food;
    }

    @FXML
    private void deleteFood(ActionEvent event) 
    {
        if (tableFood.getSelectionModel().getSelectedIndex() >= 0)
        {
            Alert dialog = new Alert(AlertType.CONFIRMATION);
            dialog.setTitle("Confirmation");
            dialog.setHeaderText("Deletion confirmation");
            dialog.setContentText("Are you sure you want to delete the selected row?");
            Optional<ButtonType> result = dialog.showAndWait();

            if (result.get() == ButtonType.OK)
            {
                food.remove(tableFood.getSelectionModel().getSelectedIndex());
                saveFile(food);
            }
        }
    }
}
