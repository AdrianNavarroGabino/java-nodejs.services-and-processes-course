// Adrián Navarro Gabino

package flightsfx.utils;

import flightsfx.model.Flight;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <h1>File Utils</h1>
 * Tools to use in the application.
 * @author Adrián Navarro Gabino
 * @version 1.0
 */
public class FileUtils {
    public static DateTimeFormatter departureFormatter =
            DateTimeFormatter.ofPattern("dd/MM/yyyy H:mm");
    public static DateTimeFormatter timeFormatter =
            DateTimeFormatter.ofPattern("H:mm");

    /**
     * Loads every flight from a txt file.
     * @return Flights list
     */
    public static List<Flight> loadFlights() {
        try {
            return Files.lines(Paths.get("flights.txt"))
                    .map(l -> new Flight(l.split(";")[0],
                            l.split(";")[1],
                            LocalDateTime.parse(
                                    l.split(";")[2], departureFormatter),
                            LocalTime.parse(
                                    l.split(";")[3], timeFormatter)))
                    .collect(Collectors.toList());
        }
        catch (Exception e) {
            System.out.println("There were some problems: " + e.getMessage() );
        }

        return new ArrayList<>();
    }

    /**
     * Saves every flight in a txt file.
     * @param flights Flights list
     */
    public static void saveFlights(List<Flight> flights)
    {
        try(PrintWriter pw = new PrintWriter("flights.txt"))
        {
            flights.stream()
                    .forEach(pw::println);
        }
        catch(Exception e)
        {
        }
    }

    /**
     * Loads a new view.
     * @param viewPath View path
     * @param stage Stage
     * @throws IOException Input/Output Exception
     */
    public static void loadScreen(String viewPath, Stage stage) throws IOException {
        Parent view1 = FXMLLoader.load(FileUtils.class.getResource(viewPath));
        Scene view1Scene = new Scene(view1);
        stage.hide();
        stage.setScene(view1Scene);
        stage.show();
    }
}
