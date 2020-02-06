package messagesfx.services;

import com.google.gson.Gson;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import messagesfx.models.responses.MessageResponse;
import messagesfx.utils.FileUtils;

/**
 * <h1>Delete message</h1>
 * Service to delete messages from the database.
 * @author Adrián Navarro Gabino
 * @version 1.0
 */
public class DeleteMessage extends Service<MessageResponse> {

    String id;

    /**
     * Performs the initialization to the message id.
     * @param id Message id
     */
    public DeleteMessage(String id) {
        this.id = id;
    }

    @Override
    protected Task<MessageResponse> createTask() {
        return new Task<MessageResponse>() {
            @Override
            protected MessageResponse call() throws Exception {
                Gson gson = new Gson();
                String json = ServiceUtils.getResponse(
                        FileUtils.HOST  + "messages/" + id,
                        null, "DELETE");

                MessageResponse resp = gson.fromJson(json, MessageResponse.class);
                return resp;
            }
        };
    }
}
