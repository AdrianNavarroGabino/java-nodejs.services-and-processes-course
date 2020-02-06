package messagesfx.services;

import com.google.gson.Gson;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import messagesfx.models.responses.LoginResponse;
import messagesfx.models.User;
import messagesfx.utils.FileUtils;

/**
 * <h1>Post login</h1>
 * Service to login in the database.
 * @author Adrián Navarro Gabino
 * @version 1.0
 */
public class PostLogin extends Service<LoginResponse> {

    private User user;

    /**
     * Performs the initialization to the user.
     * @param user User
     */
    public PostLogin(User user)
    {
        this.user = user;
    }

    @Override
    protected Task<LoginResponse> createTask() {
        return new Task<LoginResponse>() {
            @Override
            protected LoginResponse call() throws Exception {
                Gson gson = new Gson();
                String json = ServiceUtils.getResponse(
                        FileUtils.HOST + "login",
                        gson.toJson(user), "POST");
                LoginResponse response = gson.fromJson(json, LoginResponse.class);
                return response;
            }
        };
    }
}
