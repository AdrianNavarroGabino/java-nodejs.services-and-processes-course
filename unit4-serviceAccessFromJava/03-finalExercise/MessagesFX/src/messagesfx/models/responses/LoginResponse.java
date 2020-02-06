package messagesfx.models.responses;

/**
 * <h1>Login response</h1>
 * Response to login requests
 */
public class LoginResponse {

    private boolean ok;
    private String token;
    private String name;
    private String image;
    private String error;

    /**
     * Gets ok response.
     * @return Ok response
     */
    public boolean isOk() {
        return ok;
    }

    /**
     * Gets user token.
     * @return User token.
     */
    public String getToken() {
        return token;
    }

    /**
     * Gets username.
     * @return Username
     */
    public String getName() {
        return name;
    }

    /**
     * Gets user image.
     * @return User image
     */
    public String getImage() {
        return image;
    }

    /**
     * Gets error response.
     * @return Error response.
     */
    public String getError() {
        return error;
    }
}
