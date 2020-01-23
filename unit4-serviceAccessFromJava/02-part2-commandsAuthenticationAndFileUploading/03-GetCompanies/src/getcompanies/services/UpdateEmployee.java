package getcompanies.services;

import com.google.gson.Gson;
import getcompanies.models.Employee;
import getcompanies.models.EmployeeResponse;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class UpdateEmployee  extends Service<EmployeeResponse> {
    Employee employee;

    public UpdateEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    protected Task<EmployeeResponse> createTask() {
        return new Task<EmployeeResponse>() {
            @Override
            protected EmployeeResponse call() throws Exception {
                Gson gson = new Gson();
                String json = ServiceUtils.getResponse(
                        "http://localhost:8080/employee/" + employee.get_id(),
                        gson.toJson(employee), "PUT");
                EmployeeResponse resp = gson.fromJson(json, EmployeeResponse.class);
                return resp;
            }
        };
    }
}
