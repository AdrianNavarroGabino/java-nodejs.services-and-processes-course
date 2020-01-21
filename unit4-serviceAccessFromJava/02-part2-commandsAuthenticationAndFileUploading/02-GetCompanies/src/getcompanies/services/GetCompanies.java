// Adrián Navarro Gabino

package getcompanies.services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import getcompanies.models.Company;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

import java.lang.reflect.Type;
import java.util.List;

public class GetCompanies extends Service<List<Company>> {
    @Override
    protected Task<List<Company>> createTask() {
        return new Task<List<Company>>() {
            @Override
            protected List<Company> call() throws Exception {
                String json = ServiceUtils.getResponse(
                        "http://localhost:8080/company", null, "GET");
                Gson gson = new Gson();
                Type type = new TypeToken<List<Company>>(){}.getType();
                List<Company> companies = gson.fromJson(json, type);
                return companies;
            }
        };
    }
}
