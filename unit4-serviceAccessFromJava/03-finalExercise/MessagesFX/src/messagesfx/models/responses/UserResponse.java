package messagesfx.models.responses;

import messagesfx.models.User;
import java.util.List;

/**
 * <h1>User response</h1>
 * Response to users requests
 */
public class UserResponse {

    private boolean ok;
    private String error;
    private List<User> users;

    /**
     * Gets ok response.
     * @return Ok response
     */
    public boolean isOk() {
        return ok;
    }

    /**
     * Gets error response.
     * @return Error response.
     */
    public String getError() {
        return error;
    }

    /**
     * Gets users response.
     * @return Users response
     */
    public List<User> getUsers() {
        return users;
    }
}
