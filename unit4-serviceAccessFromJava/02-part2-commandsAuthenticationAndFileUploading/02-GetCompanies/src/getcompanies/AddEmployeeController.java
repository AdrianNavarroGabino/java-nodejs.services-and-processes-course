package getcompanies;

import getcompanies.models.Company;
import getcompanies.models.Employee;
import getcompanies.models.EmployeeResponse;
import getcompanies.services.PostEmployee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddEmployeeController {

    @FXML
    private TextField nifBox;

    @FXML
    private TextField nameBox;

    @FXML
    private TextField ageBox;

    @FXML
    private Label statusLbl;

    @FXML
    private Button addBtn;

    @FXML
    private Button cancelBtn;

    @FXML
    void add(ActionEvent event) {
        Company company = FXMLDocumentController.selectedCompany;
        if(company == null)
        {
            cancel(event);
            return;
        }

        if(nifBox.getText().isEmpty() || nameBox.getText().isEmpty() ||
                ageBox.getText().isEmpty())
        {
            statusLbl.setText("Fields must not be empty");
        } else {
            Employee employee = new Employee(nifBox.getText(),
                    nameBox.getText(), Integer.parseInt(ageBox.getText()));
            PostEmployee add = new PostEmployee(employee, company.get_id());

            add.start();
            add.setOnSucceeded((e) -> {
                EmployeeResponse resp = add.getValue();
                if(!resp.isOk()) {
                    statusLbl.setText(resp.getError());
                } else {
                    employee.set_id(resp.getId());
                    company.getEmployees().add(employee);
                    Stage window = (Stage) ((Node) event.getSource())
                            .getScene().getWindow();
                    window.close();
                }
            });
        }
    }

    @FXML
    void cancel(ActionEvent event) {
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.close();
    }

}
