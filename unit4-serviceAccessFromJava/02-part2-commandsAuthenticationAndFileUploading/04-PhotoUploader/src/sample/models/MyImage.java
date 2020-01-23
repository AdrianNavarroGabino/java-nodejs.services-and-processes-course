// Adrián Navarro Gabino

package sample.models;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;

public class MyImage {
    String name;
    String data;
    String title;
    String desc;

    public MyImage(Path file, String title, String desc) {

        this.title = title;
        this.desc = desc;

        name = file.getFileName().toString();

        byte[] fileData;
        data = "";
        try {
            fileData = Files.readAllBytes(file);
            data = Base64.getEncoder().encodeToString(fileData);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
