package getcompanies;

import getcompanies.models.Employee;
import getcompanies.models.EmployeeResponse;
import getcompanies.services.UpdateEmployee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class UpdateEmployeeController implements Initializable {

    @FXML
    private TextField nifBox;

    @FXML
    private TextField nameBox;

    @FXML
    private TextField ageBox;

    @FXML
    private Button okBtn;

    @FXML
    private Button cancelBtn;

    @FXML
    private Label statusLbl;

    private Employee selectedEmployee;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        selectedEmployee = FXMLDocumentController.selectedEmployee;
        nifBox.setText(selectedEmployee.getNif());
        nameBox.setText(selectedEmployee.getName());
        ageBox.setText(String.valueOf(selectedEmployee.getAge()));
    }

    @FXML
    void cancel(ActionEvent event) {
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.close();
    }

    @FXML
    void update(ActionEvent event) {
        if(nifBox.getText().isEmpty() || nameBox.getText().isEmpty() ||
                ageBox.getText().isEmpty()) {
            statusLbl.setText("Fields must not be empty");
        } else {
            Employee employee = new Employee(nifBox.getText(),
                    nameBox.getText(), Integer.parseInt(ageBox.getText()));
            employee.set_id(selectedEmployee.get_id());
            UpdateEmployee update = new UpdateEmployee(employee);

            okBtn.setDisable(true);
            update.start();
            update.setOnSucceeded((e) -> {
                okBtn.setDisable(false);
                EmployeeResponse resp = update.getValue();

                if(!resp.isOk()) {
                    statusLbl.setText(resp.getError());
                } else {
                    selectedEmployee.setAge(employee.getAge());
                    selectedEmployee.setNif(employee.getNif());
                    selectedEmployee.setName(employee.getName());
                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    window.close();
                }
            });
        }
    }
}
