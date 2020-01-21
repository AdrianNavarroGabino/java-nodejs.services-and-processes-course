package getcompanies.services;

import com.google.gson.Gson;
import getcompanies.models.Employee;
import getcompanies.models.EmployeeResponse;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class PostEmployee extends Service<EmployeeResponse> {

    Employee employee;
    String idCompany;

    public PostEmployee(Employee employee, String idCompany) {
        this.employee = employee;
        this.idCompany = idCompany;
    }

    @Override
    protected Task<EmployeeResponse> createTask() {
        return new Task<EmployeeResponse>() {
            @Override
            protected EmployeeResponse call() throws Exception {
                Gson gson = new Gson();
                String json = ServiceUtils.getResponse(
                        "http://localhost:8080/employee/" + idCompany,
                        gson.toJson(employee), "POST");
                EmployeeResponse response = gson.fromJson(
                        json, EmployeeResponse.class);
                return response;
            }
        };
    }
}
