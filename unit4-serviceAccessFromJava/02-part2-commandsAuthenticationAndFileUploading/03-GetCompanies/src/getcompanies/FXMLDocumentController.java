// Adrián Navarro Gabino
package getcompanies;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import getcompanies.models.Company;
import getcompanies.models.Employee;
import getcompanies.models.CompanyResponse;
import getcompanies.models.EmployeeResponse;
import getcompanies.services.DeleteEmployee;
import getcompanies.services.GetCompany;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import getcompanies.services.GetCompanies;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FXMLDocumentController implements Initializable {
    
    @FXML private ListView<Company> listCompanies;
    @FXML private ListView<Employee> listEmployees;

    public static Company selectedCompany;
    public static Employee selectedEmployee;

    @FXML
    private Button addBtn;

    @FXML
    private Button updateBtn;

    @FXML
    private Button deleteBtn;

    @FXML
    void addEmployee(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);

        Parent root =
                FXMLLoader.load(getClass().getResource("AddEmployee.fxml"));

        Scene scene = new Scene(root);

        stage.setTitle("Add employee");
        stage.setScene(scene);
        stage.show();
        stage.setOnHidden((e) -> {
            listEmployees.setItems(
                    FXCollections.observableArrayList(
                            selectedCompany.getEmployees()));
        });
    }

    @FXML
    void deleteEmployee(ActionEvent event) {
        Employee employee = listEmployees.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete employee " + employee.getNif());
        alert.setHeaderText("Delete the employee '" +
                employee.getName() + "' with NIF: " + employee.getNif() + "");
        alert.setContentText("Are you ok with this?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            DeleteEmployee delete = new DeleteEmployee(employee.get_id());
            delete.start();

            delete.setOnSucceeded(e -> {
                EmployeeResponse resp = delete.getValue();
                if(resp.isOk()) { // Success
                    listEmployees.getItems().remove(employee);
                }
            });
        }
    }

    @FXML
    void updateEmployee(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);

        Parent root = FXMLLoader.load(getClass().getResource("UpdateEmployee.fxml"));

        Scene scene = new Scene(root);

        stage.setTitle("Update");
        stage.setScene(scene);
        stage.show();
        stage.setOnHidden((e) -> {
            listEmployees.refresh();
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        GetCompanies getComps = new GetCompanies();
        getComps.start();
        getComps.setOnSucceeded((e) -> {
            listCompanies.setItems(FXCollections.observableArrayList(
                    getComps.getValue()));
        });

        listCompanies.getSelectionModel().selectedIndexProperty().addListener(
                (ov, old_val, new_val) -> {
                    if(new_val.intValue() >=0 ) {
                        GetCompany getCompany = new GetCompany(
                                listCompanies.getItems()
                                        .get(new_val.intValue()).get_id());
                        getCompany.start();

                        getCompany.setOnSucceeded((e) -> {
                            CompanyResponse resp = getCompany.getValue();
                            if(resp.isOk()) {
                                listEmployees.setItems(
                                        FXCollections.observableArrayList(
                                                resp.getCompany()
                                                        .getEmployees()));
                                selectedCompany = resp.getCompany();
                            } else {
                                System.err.println(resp.getError());
                            }
                        });
                    }
                }
        );

        listEmployees.getSelectionModel().selectedIndexProperty().addListener(
                (ov, oldVal, newVal) -> {
                    if(newVal.intValue() >= 0) {
                        selectedEmployee = listEmployees
                                .getSelectionModel().getSelectedItem();
                        updateBtn.setDisable(false);
                        deleteBtn.setDisable(false);
                    }
                    else {
                        updateBtn.setDisable(true);
                        deleteBtn.setDisable(true);
                    }
                });
    }    
    
}
