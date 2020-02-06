package messagesfx.models.responses;

import messagesfx.models.Message;
import java.util.List;

/**
 * <h1>Message response</h1>
 * Response to mesagges requests
 */
public class MessageResponse {

    private boolean ok;
    private String error;
    private Message message;
    private List<Message> messages;

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
     * Gets message response.
     * @return Message response
     */
    public Message getMessage() {
        return message;
    }

    /**
     * Gets messages response.
     * @return Messages response
     */
    public List<Message> getMessages() {
        return messages;
    }
}
