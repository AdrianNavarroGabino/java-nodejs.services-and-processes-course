// Adrián Navarro Gabino
package getcompanies;

import java.net.URL;
import java.util.ResourceBundle;
import getcompanies.models.Company;
import getcompanies.models.Employee;
import getcompanies.models.GetResponse;
import getcompanies.services.GetCompany;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import getcompanies.services.GetCompanies;

public class FXMLDocumentController implements Initializable {
    
    @FXML private ListView<Company> listCompanies;
    @FXML private ListView<Employee> listEmployees;
        
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
                            GetResponse resp = getCompany.getValue();
                            if(resp.isOk()) {
                                listEmployees.setItems(
                                        FXCollections.observableArrayList(
                                                resp.getCompany()
                                                        .getEmployees()));
                            } else {
                                System.err.println(resp.getError());
                            }
                        });
                    }
                }
        );
    }    
    
}
