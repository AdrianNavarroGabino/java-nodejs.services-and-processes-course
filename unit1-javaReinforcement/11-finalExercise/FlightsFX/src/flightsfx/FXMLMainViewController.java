// Adrián Navarro Gabino

package flightsfx;

import flightsfx.model.Flight;
import flightsfx.utils.FileUtils;
import flightsfx.utils.MessageUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

public class FXMLMainViewController {
    private static ObservableList<Flight> flights;

    @FXML
    private TableView<Flight> flightsTable;

    @FXML
    private TableColumn<Flight, String> flightNumberColumn;

    @FXML
    private TableColumn<Flight, String> destinationColumn;

    @FXML
    private TableColumn<Flight, LocalDateTime> departureColumn;

    @FXML
    private TableColumn<Flight, LocalTime> durationColumn;

    @FXML
    private ChoiceBox<String> choiceFilter;

    @FXML
    private Button deleteBtn;

    @FXML
    private Button applyBtn;

    @FXML
    private TextField flightNumberTxt;

    @FXML
    private TextField destinationTxt;

    @FXML
    private TextField departureTxt;

    @FXML
    private TextField durationTxt;

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

    public void showAllFlights()
    {
        flightsTable.setItems(flights);
    }

    public void showFlightsCurrentCity()
    {
        if(flightsTable.getSelectionModel().getSelectedIndex() != -1)
        {
            List<Flight> flightsCurrentCity = flights.stream()
                    .filter(f -> f.getDestination().equalsIgnoreCase(
                            flightsTable.getSelectionModel()
                                    .getSelectedItem().getDestination()
                    ))
                    .collect(Collectors.toList());
            ObservableList<Flight> auxFlights =
                    FXCollections.observableArrayList(flightsCurrentCity);
            flightsTable.setItems(auxFlights);
        }
        else
        {
            MessageUtils.showError("No flight selected");
        }
    }
    public void showLongFlights()
    {
        List<Flight> longFlights = flights.stream()
                .filter(f -> f.getDuration()
                        .compareTo(LocalTime.of(3,0,0)) > 0)
                .collect(Collectors.toList());
        ObservableList<Flight> auxFlights =
                FXCollections.observableArrayList(longFlights);
        flightsTable.setItems(auxFlights);
    }

    public void showNextFiveFlights()
    {
        Comparator<Flight> comparator = (a,b) -> a.getDepartureDate()
                .compareTo(b.getDepartureDate());

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

    public void showFlightDurationAverage()
    {
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

                flightNumberTxt.setText("");
                departureTxt.setText("");
                destinationTxt.setText("");
                durationTxt.setText("");
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

    public void deleteFlight(ActionEvent actionEvent) {
        Flight flight = flightsTable.getSelectionModel().getSelectedItem();
        flights.remove(flight);
        flightsTable.getSelectionModel().clearSelection();
        deleteBtn.setDisable(true);
    }

    public static List<Flight> getFlights() { return flights; }

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

                    flightNumberTxt.setText("");
                    departureTxt.setText("");
                    destinationTxt.setText("");
                    durationTxt.setText("");
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

    public void goToChartView(ActionEvent actionEvent) throws IOException {
        FileUtils.loadScreen("/flightsfx/chartview/ChartView.fxml",
                (Stage) ((Node) actionEvent.getSource())
                        .getScene().getWindow());
    }
}
