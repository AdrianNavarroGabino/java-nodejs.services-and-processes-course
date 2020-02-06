package messagesfx.services;

import com.google.gson.Gson;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import messagesfx.models.responses.UserResponse;
import messagesfx.utils.FileUtils;

/**
 * <h1>Get users</h1>
 * Service to get users from the database.
 * @author Adrián Navarro Gabino
 * @version 1.0
 */
public class GetUsers extends Service<UserResponse> {
    @Override
    protected Task<UserResponse> createTask() {
        return new Task<UserResponse>() {
            @Override
            protected UserResponse call() throws Exception {
                String json = ServiceUtils.getResponse(
                        FileUtils.HOST + "users", null, "GET");
                Gson gson = new Gson();
                UserResponse users = gson.fromJson(json, UserResponse.class);
                return users;
            }
        };
    }
}
