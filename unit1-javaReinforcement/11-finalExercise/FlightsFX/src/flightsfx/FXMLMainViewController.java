package flightsfx;

import flightsfx.model.Flight;
import flightsfx.utils.FileUtils;
import flightsfx.utils.MessageUtils;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.time.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <h1>Main View Controller</h1>
 * Manages the main view.
 * @author Adrián Navarro Gabino
 * @version 1.0
 */
public class FXMLMainViewController {
    private static ObservableList<Flight> flights;

    @FXML
    private TableView<Flight> flightsTable;
    @FXML
    private TableColumn<Flight, String> flightNumberColumn, destinationColumn;
    @FXML
    private TableColumn<Flight, LocalDateTime> departureColumn;
    @FXML
    private TableColumn<Flight, LocalTime> durationColumn;
    @FXML
    private ChoiceBox<String> choiceFilter;
    @FXML
    private Button deleteBtn;
    @FXML
    private TextField flightNumberTxt, destinationTxt, departureTxt, durationTxt;

    /**
     * Initializes the FXML view.
     */
    public void initialize() {
        deleteBtn.setDisable(true);
        flightsTable.setPlaceholder(new Label("No flights available"));
        flightNumberColumn.setCellValueFactory(
                new PropertyValueFactory("flightNumber"));
        destinationColumn.setCellValueFactory(
                new PropertyValueFactory("destination"));
        departureColumn.setCellValueFactory(
                new PropertyValueFactory("departure"));
        durationColumn.setCellValueFactory(
                new PropertyValueFactory("duration"));
        flights = FXCollections.observableArrayList(FileUtils.loadFlights());
        showAllFlights();
        fillFilterChoice();
    }

    /**
     * Shows every flight from the flights list in the table.
     */
    public void showAllFlights()
    {
        flightsTable.setItems(flights);
    }

    /**
     * Shows all the flights to the city of the currently selected flight in
     * the table. If no flight is selected, then this choice will cause an error
     * alert.
     */
    public void showFlightsCurrentCity()
    {
        if(flightsTable.getSelectionModel().getSelectedIndex() != -1)
        {
            // Collectors.toList() doesn't admit an ObservableList so I need an
            // auxiliary variable.
            List<Flight> flightsCurrentCity = flights.stream()
                    .filter(f -> f.getDestination().equalsIgnoreCase(
                            flightsTable.getSelectionModel()
                                    .getSelectedItem().getDestination()
                    ))
                    .collect(Collectors.toList());
            ObservableList<Flight> auxFlights =
                    FXCollections.observableArrayList(flightsCurrentCity);
            flightsTable.setItems(auxFlights);
            emptyFields();
        }
        else
        {
            MessageUtils.showError("No flight selected");
        }
    }

    /**
     * Shows all the flights whose duration is longer than 3 hours.
     */
    public void showLongFlights()
    {
        // Collectors.toList() doesn't admit an ObservableList so I need an
        // auxiliary variable.
        List<Flight> longFlights = flights.stream()
                .filter(f -> f.getDuration()
                        .compareTo(LocalTime.of(3,0,0)) > 0)
                .collect(Collectors.toList());
        ObservableList<Flight> auxFlights =
                FXCollections.observableArrayList(longFlights);
        flightsTable.setItems(auxFlights);
    }

    /**
     * Shows only the next 5 flights that will depart from the airport.
     */
    public void showNextFiveFlights()
    {
        Comparator<Flight> comparator =
                Comparator.comparing(Flight::getDepartureDate);

        // Collectors.toList() doesn't admit an ObservableList so I need an
        // auxiliary variable.
        List<Flight> longFlights = flights.stream()
                .filter(f -> f.getDepartureDate()
                        .compareTo(LocalDateTime.now()) > 0)
                .sorted(comparator)
                .limit(5)
                .collect(Collectors.toList());
        ObservableList<Flight> auxFlights =
                FXCollections.observableArrayList(longFlights);
        flightsTable.setItems(auxFlights);
    }

    /**
     * Calculates the duration average of all the flights, and show the result
     * in an Alert.
     */
    public void showFlightDurationAverage()
    {
        // .average() returns an OptionalDouble, which is a container object
        // which may or may not contain a double value.
        OptionalDouble averageMinutes = flights.stream()
                .mapToInt(f ->
                        f.getDuration().getHour() * 60 +
                                f.getDuration().getMinute())
                .average();

        LocalTime average = LocalTime.of(
                (int)averageMinutes.getAsDouble() / 60,
                (int)averageMinutes.getAsDouble() % 60);

        MessageUtils.showMessage("Flight duration average: " + average);
    }

    /**
     * Fills the filter choice with the filter options.
     */
    public void fillFilterChoice()
    {
        choiceFilter.setItems(
                FXCollections.observableArrayList(
                        "Show all flights",
                        "Show flights to currently selected city",
                        "Show long flights",
                        "Show next 5 flights",
                        "Show flight duration average"));
    }

    /**
     * Adds a new flight to the list and to the table.
     * @param actionEvent Action event
     */
    public void addFlight(ActionEvent actionEvent) {
        if(!flightNumberTxt.getText().equals("") &&
                !departureTxt.getText().equals("") &&
                !destinationTxt.getText().equals("") &&
                !durationTxt.getText().equals(""))
        {
            try {
                flights.add(new Flight(flightNumberTxt.getText(),
                        destinationTxt.getText(),
                        LocalDateTime.parse(
                                departureTxt.getText(),
                                FileUtils.departureFormatter),
                        LocalTime.parse(
                                durationTxt.getText(), FileUtils.timeFormatter)));

                // If you add a flight with a filter on, you need to reload
                // the table
                applyFilter(actionEvent);
                emptyFields();
            }
            catch (Exception e)
            {
                MessageUtils.showError("Invalid fields");
            }
        }
        else
        {
            MessageUtils.showError("There should be no empty fields");
        }
    }

    /**
     * Actives delete button when a flight is selected.
     * @param mouseEvent Mouse event
     */
    public void activeDelete(MouseEvent mouseEvent) {
        if(flightsTable.getSelectionModel().getSelectedIndex() != -1)
        {
            Flight flight = flightsTable.getSelectionModel().getSelectedItem();
            flightNumberTxt.setText(flight.getFlightNumber());
            departureTxt.setText(flight.getDeparture());
            destinationTxt.setText(flight.getDestination());
            durationTxt.setText(
                    flight.getDuration().format(FileUtils.timeFormatter));
            deleteBtn.setDisable(false);
        }
    }

    /**
     * Deletes a selected flight.
     * @param actionEvent Action event
     */
    public void deleteFlight(ActionEvent actionEvent) {
        Flight flight = flightsTable.getSelectionModel().getSelectedItem();
        flights.remove(flight);
        // If you delete a flight with a filter on, you need to reload
        // the table
        applyFilter(actionEvent);
        flightsTable.getSelectionModel().clearSelection();
        emptyFields();
        deleteBtn.setDisable(true);
    }

    /**
     * Gets the flights list
     * @return Flights list
     */
    public static List<Flight> getFlights() { return flights; }

    /**
     * Apply the chosen filter by clicking Apply Filter button.
     * @param actionEvent Action event
     */
    public void applyFilter(ActionEvent actionEvent) {
        deleteBtn.setDisable(true);
        switch(choiceFilter.getSelectionModel().getSelectedIndex())
        {
            case 0: showAllFlights(); break;
            case 1: showFlightsCurrentCity(); break;
            case 2: showLongFlights(); break;
            case 3: showNextFiveFlights(); break;
            case 4: showFlightDurationAverage(); break;
        }
        flightsTable.getSelectionModel().clearSelection();
    }

    /**
     * Updates a flight data without having to delete and add it again to the
     * list.
     * @param actionEvent Action event
     */
    public void modify(ActionEvent actionEvent) {
        if(flightsTable.getSelectionModel().getSelectedIndex() != -1){
            if(!flightNumberTxt.getText().equals("") &&
                    !departureTxt.getText().equals("") &&
                    !destinationTxt.getText().equals("") &&
                    !durationTxt.getText().equals("")){
                try{
                    Flight flight =
                            flightsTable.getSelectionModel().getSelectedItem();
                    flight.setFlightNumber(flightNumberTxt.getText());
                    flight.setDestination(destinationTxt.getText());
                    flight.setDeparture(LocalDateTime.parse(
                            departureTxt.getText(),
                            FileUtils.departureFormatter));
                    flight.setDuration(LocalTime.parse(
                            durationTxt.getText(), FileUtils.timeFormatter));
                    flightsTable.refresh();

                    emptyFields();
                    flightsTable.getSelectionModel().clearSelection();
                    deleteBtn.setDisable(true);
                }
                catch(Exception e){
                    MessageUtils.showError("Invalid fields");
                }
            }
            else{
                MessageUtils.showError("There should be no empty fields");
            }
        }
        else{
            MessageUtils.showError("You must select a flight");
        }
    }

    /**
     * Changes the view to show a pie chart with the number of flights per
     * destination.
     * @param actionEvent Action event
     * @throws IOException Input/Output Exception
     */
    public void goToChartView(ActionEvent actionEvent) throws IOException {
        FileUtils.loadScreen("/flightsfx/chartview/ChartView.fxml",
                (Stage) ((Node) actionEvent.getSource())
                        .getScene().getWindow());
    }

    /**
     * Empties flight number, destination, departure and duration fields.
     */
    public void emptyFields()
    {
        flightNumberTxt.setText("");
        departureTxt.setText("");
        destinationTxt.setText("");
        durationTxt.setText("");
    }
}
