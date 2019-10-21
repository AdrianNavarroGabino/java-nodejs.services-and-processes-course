// Adrián Navarro Gabino

package flightsfx.model;

import flightsfx.utils.FileUtils;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 *<h1>Flight</h1>
 * Class to define flights with their flight number, destination, departure and
 * duration.
 * @author Adrián Navarro Gabino
 * @version 1.0
 */
public class Flight
{
    private String flightNumber;
    private String destination;
    private LocalDateTime departure;
    private LocalTime duration;

    /**
     * Performs the initialization to the flight number.
     * @param flightNumber Flight number
     */
    public Flight(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    /**
     * Performs the complete initialization to the flight in order to save and
     * load them.
     * @param flightNumber Flight number
     * @param destination Flight destination
     * @param departure Flight departure
     * @param duration Flight duration
     */
    public Flight(String flightNumber, String destination,
                  LocalDateTime departure, LocalTime duration) {
        this.flightNumber = flightNumber;
        this.destination = destination;
        this.departure = departure;
        this.duration = duration;
    }

    /**
     * Gets the flight number.
     * @return Flight number
     */
    public String getFlightNumber() {
        return flightNumber;
    }

    /**
     * Gets the flight destination.
     * @return Flight destination
     */
    public String getDestination() {
        return destination;
    }

    /**
     * Gets the flight departure as a String.
     * @return Flight departure
     */
    public String getDeparture() {
        return departure.format(FileUtils.departureFormatter);
    }

    /**
     * Gets the flight departure as a LocalDateTime.
     * @return Flight departure
     */
    public LocalDateTime getDepartureDate() { return departure; }

    /**
     * Gets the flight duration.
     * @return Flight duration
     */
    public LocalTime getDuration() { return duration; }

    /**
     * Sets the flight number.
     * @param flightNumber Flight number
     */
    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    /**
     * Sets the flight destination.
     * @param destination Flight destination
     */
    public void setDestination(String destination) {
        this.destination = destination;
    }

    /**
     * Sets the flight departure.
     * @param departure Flight departure
     */
    public void setDeparture(LocalDateTime departure) {
        this.departure = departure;
    }

    /**
     * Sets the flight duration.
     * @param duration Flight duration
     */
    public void setDuration(LocalTime duration) {
        this.duration = duration;
    }

    /**
     * Overrides toString method to get the flight data.
     * @return Flight data
     */
    @Override
    public String toString() {
        return flightNumber + ";" + destination + ";" +
                getDeparture()+ ";" + duration;
    }
}
