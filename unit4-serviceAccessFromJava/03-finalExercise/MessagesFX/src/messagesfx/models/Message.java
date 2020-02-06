package messagesfx.models;

import javafx.scene.image.ImageView;
import messagesfx.utils.FileUtils;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *<h1>Message</h1>
 * Class to define messages with their db id, sender, recipient, message body,
 * image (if any) and sent date.
 * @author Adrián Navarro Gabino
 * @version 1.0
 */
public class Message {
    private String _id;
    private User from;
    private String to;
    private String message;
    private String image;
    private LocalDate sent;

    /**
     * Performs the initialization.
     */
    public Message()
    {

    }

    /**
     * Performs the complete initialization to the message.
     * @param from Sender
     * @param to Recipient
     * @param message Message body
     * @param image Image
     * @param sent Sent date
     */
    public Message(User from, String to, String message, String image, LocalDate sent) {
        this.from = from;
        this.to = to;
        this.message = message;
        this.image = image;
        this.sent = sent;
    }

    /**
     * Gets the sender.
     * @return Sender
     */
    public User getFrom() {
        return from;
    }

    /**
     * Gets the recipient.
     * @return Recipient
     */
    public String getTo() {
        return to;
    }

    /**
     * Gets the message body.
     * @return Message body
     */
    public String getMessage() {
        return message;
    }

    /**
     * Gets the image.
     * @return Message image
     */
    public String getImage() {
        return image;
    }

    /**
     * Gets the sent date.
     * @return Sent date
     */
    public String getSent() {
        return sent.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    /**
     * Sets the sender.
     * @param from Sender
     */
    public void setFrom(User from) {
        this.from = from;
    }

    /**
     * Sets the recipient.
     * @param to Recipient
     */
    public void setTo(String to) {
        this.to = to;
    }

    /**
     * Sets the message body.
     * @param message Message body
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Sets the image.
     * @param image Message image
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * Sets the sent date.
     * @param sent Sent date
     */
    public void setSent(LocalDate sent) {
        this.sent = sent;
    }

    /**
     * Gets the database id.
     * @return Database id
     */
    public String get_id() {
        return _id;
    }

    /**
     * Gets the message image with ImageView format.
     * @return Message image with ImageView format
     */
    public ImageView getImageView() {
        ImageView imgView = new ImageView(FileUtils.HOST + image);
        imgView.setFitHeight(30);
        imgView.setPreserveRatio(true);
        return imgView;
    }
}
