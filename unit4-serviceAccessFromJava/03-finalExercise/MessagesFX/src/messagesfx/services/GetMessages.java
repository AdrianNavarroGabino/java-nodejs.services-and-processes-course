package messagesfx.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import messagesfx.models.responses.MessageResponse;
import messagesfx.utils.FileUtils;
import java.time.LocalDate;
import java.time.ZonedDateTime;

/**
 * <h1>Get messages</h1>
 * Service to get messages from the database.
 * @author Adrián Navarro Gabino
 * @version 1.0
 */
public class GetMessages extends Service<MessageResponse> {
    @Override
    protected Task<MessageResponse> createTask() {
            return new Task<MessageResponse>() {
        @Override
        protected MessageResponse call() throws Exception {
                Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class,
                    (JsonDeserializer<LocalDate>) (json, type, jsonDeserializationContext) -> {
                        return ZonedDateTime.parse(json.getAsJsonPrimitive().getAsString()).toLocalDate();
                    }).create();
                String json = ServiceUtils.getResponse(
                        FileUtils.HOST + "messages", null, "GET");
                MessageResponse messages = gson.fromJson(json, MessageResponse.class);
                return messages;
            }
        };
    }
}