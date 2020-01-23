package sample.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import sample.models.MyImage;
import sample.models.MyImageResponse;

public class PostImage extends Service<MyImageResponse> {
    MyImage image;

    public PostImage(MyImage image) {
        this.image = image;
    }

    @Override
    protected Task<MyImageResponse> createTask() {
        return new Task<MyImageResponse>() {
            @Override
            protected MyImageResponse call() throws Exception {
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                String json = ServiceUtils.getResponse(
                        "http://localhost:8080/photo",
                        gson.toJson(image), "POST");
                MyImageResponse response = gson.fromJson(json, MyImageResponse.class);
                return response;
            }
        };
    }
}
