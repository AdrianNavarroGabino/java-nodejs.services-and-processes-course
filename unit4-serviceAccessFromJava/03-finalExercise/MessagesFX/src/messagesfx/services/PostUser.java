package messagesfx.services;

import com.google.gson.Gson;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import messagesfx.models.User;
import messagesfx.models.responses.UserResponse;
import messagesfx.utils.FileUtils;

/**
 * <h1>Post user</h1>
 * Service to post users to the database.
 * @author Adrián Navarro Gabino
 * @version 1.0
 */
public class PostUser extends Service<UserResponse> {

    private User user;

    /**
     * Performs the initialization to the user.
     * @param user User
     */
    public PostUser(User user)
    {
        this.user = user;
    }

    @Override
    protected Task<UserResponse> createTask() {
        return new Task<UserResponse>() {
            @Override
            protected UserResponse call() throws Exception {
                Gson gson = new Gson();
                String json = ServiceUtils.getResponse(
                        FileUtils.HOST + "register",
                        gson.toJson(user), "POST");
                UserResponse response = gson.fromJson(json, UserResponse.class);
                return response;
            }
        };
    }
}
