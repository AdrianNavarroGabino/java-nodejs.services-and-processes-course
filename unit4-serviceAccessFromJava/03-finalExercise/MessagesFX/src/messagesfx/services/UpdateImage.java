package messagesfx.services;

import com.google.gson.Gson;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import messagesfx.models.User;
import messagesfx.models.responses.UserResponse;
import messagesfx.utils.FileUtils;

/**
 * <h1>Update image</h1>
 * Service to update images to the database.
 * @author Adrián Navarro Gabino
 * @version 1.0
 */
public class UpdateImage extends Service<UserResponse> {

    private User user;

    /**
     * Performs the initialization to the user.
     * @param user User
     */
    public UpdateImage(User user) {
        this.user = user;
    }

    @Override
    protected Task<UserResponse> createTask() {
        return new Task<UserResponse>() {
            @Override
            protected UserResponse call() throws Exception {
                Gson gson = new Gson();
                String json = ServiceUtils.getResponse(
                        FileUtils.HOST + "users",
                        gson.toJson(user), "PUT");
                UserResponse resp = gson.fromJson(json, UserResponse.class);
                return resp;
            }
        };
    }
}
