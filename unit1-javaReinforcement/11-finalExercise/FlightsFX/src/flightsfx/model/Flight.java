// Adrián Navarro Gabino

package flightsfx.model;

import flightsfx.utils.FileUtils;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Flight
{
    private String flightNumber;
    private String destination;
    private LocalDateTime departure;
    private LocalTime duration;

    public Flight(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public Flight(String flightNumber, String destination,
                  LocalDateTime departure, LocalTime duration) {
        this.flightNumber = flightNumber;
        this.destination = destination;
        this.departure = departure;
        this.duration = duration;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getDestination() {
        return destination;
    }

    public String getDeparture() {
        return departure.format(FileUtils.departureFormatter);
    }

    public LocalDateTime getDepartureDate() { return departure; }

    public LocalTime getDuration() { return duration; }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setDeparture(LocalDateTime departure) {
        this.departure = departure;
    }

    public void setDuration(LocalTime duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return flightNumber + ";" + destination + ";" +
                getDeparture()+ ";" + duration;
    }
}
