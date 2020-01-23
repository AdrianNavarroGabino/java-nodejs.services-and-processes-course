package getcompanies.services;

import com.google.gson.Gson;
import getcompanies.models.EmployeeResponse;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class DeleteEmployee  extends Service<EmployeeResponse> {
    String id;

    public DeleteEmployee(String id) {
        this.id = id;
    }

    @Override
    protected Task<EmployeeResponse> createTask() {
        return new Task<EmployeeResponse>() {
            @Override
            protected EmployeeResponse call() throws Exception {
                Gson gson = new Gson();
                String json = ServiceUtils.getResponse(
                        "http://localhost:8080/employee/" + id,
                        null, "DELETE");

                EmployeeResponse resp = gson.fromJson(json, EmployeeResponse.class);
                return resp;
            }
        };
    }
}
