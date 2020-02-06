package messagesfx.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import messagesfx.models.Message;
import messagesfx.models.responses.MessageResponse;
import messagesfx.utils.FileUtils;

import java.time.LocalDate;

/**
 * <h1>Post message</h1>
 * Service to post images to the database.
 * @author Adrián Navarro Gabino
 * @version 1.0
 */
public class PostMessage extends Service<MessageResponse> {

    Message message;

    /**
     * Performs the initialization to the message.
     * @param message Message
     */
    public PostMessage(Message message)
    {
        this.message = message;
    }

    @Override
    protected Task<MessageResponse> createTask() {
        return new Task<MessageResponse>() {
            @Override
            protected MessageResponse call() throws Exception {
                Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class,
                        (JsonSerializer<LocalDate>)(date, typeOfSrc, context) -> {
                            return new JsonPrimitive(date.toString());
                        }).create();
                String json = ServiceUtils.getResponse(
                        FileUtils.HOST + "messages/" + message.getTo(),
                        gson.toJson(message), "POST");
                MessageResponse response = gson.fromJson(json, MessageResponse.class);
                System.out.println(response.getMessage());
                return response;
            }
        };
    }
}
