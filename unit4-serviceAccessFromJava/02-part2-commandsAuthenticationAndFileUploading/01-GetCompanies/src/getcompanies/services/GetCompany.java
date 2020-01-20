// Adrián Navarro Gabino

package getcompanies.services;

import com.google.gson.Gson;
import getcompanies.models.GetResponse;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class GetCompany extends Service<GetResponse> {
    String id;

    public GetCompany(String id) {
        this.id = id;
    }

    @Override
    protected Task<GetResponse> createTask() {
        return new Task<GetResponse>() {
            @Override
            protected GetResponse call() throws Exception {
                String json = ServiceUtils.getResponse(
                        "http://localhost:8080/company/" + id, null, "GET");

                Gson gson = new Gson();
                GetResponse comp = gson.fromJson(json, GetResponse.class);
                return comp;
            }
        };
    }
}
