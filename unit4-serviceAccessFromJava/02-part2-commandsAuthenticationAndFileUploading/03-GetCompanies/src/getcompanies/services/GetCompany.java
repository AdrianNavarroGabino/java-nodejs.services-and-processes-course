// Adrián Navarro Gabino

package getcompanies.services;

import com.google.gson.Gson;
import getcompanies.models.CompanyResponse;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class GetCompany extends Service<CompanyResponse> {
    String id;

    public GetCompany(String id) {
        this.id = id;
    }

    @Override
    protected Task<CompanyResponse> createTask() {
        return new Task<CompanyResponse>() {
            @Override
            protected CompanyResponse call() throws Exception {
                String json = ServiceUtils.getResponse(
                        "http://localhost:8080/company/" + id, null, "GET");

                Gson gson = new Gson();
                CompanyResponse comp = gson.fromJson(json, CompanyResponse.class);
                return comp;
            }
        };
    }
}
