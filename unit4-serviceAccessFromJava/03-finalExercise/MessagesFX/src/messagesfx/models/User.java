package messagesfx.models;

import javafx.scene.image.ImageView;
import messagesfx.utils.FileUtils;

/**
 *<h1>User</h1>
 * Class to define users with their db id, name, password and image.
 * @author Adrián Navarro Gabino
 * @version 1.0
 */
public class User
{
    private String _id;
    private String name;
    private String password;
    private String image;

    /**
     * Performs the initialization to the name and password.
     * @param name Username
     * @param password Password
     */
    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    /**
     * Performs the complete initialization to the user.
     * @param name Username
     * @param password Password
     * @param image User image
     */
    public User(String name, String password, String image) {
        this.name = name;
        this.password = password;
        this.image = image;
    }

    /**
     * Gets the username.
     * @return Username
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the user password.
     * @return User password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Gets the user image.
     * @return User image
     */
    public String getImage() {
        return image;
    }

    /**
     * Sets the username.
     * @param name Username
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the user password.
     * @param password User password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Sets the user image.
     * @param image User image.
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * Gets the database id.
     * @return Database id
     */
    public String get_id() {
        return _id;
    }

    /**
     * Gets the user image with ImageView format.
     * @return User image with ImageView format
     */
    public ImageView getImageView() {
        ImageView imgView = new ImageView(FileUtils.HOST + image);
        imgView.setFitHeight(30);
        imgView.setPreserveRatio(true);
        return imgView;
    }
}
