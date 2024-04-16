package entity;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Flight extends AbstractEntity {
    String departure;
    String destination;
    int availableSeats;
    long dateTimeOfDeparture;

    private final transient DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy, HH:mm");

    public Flight(String departure, String destination, int availableSeats, long dateTimeOfDeparture) {
        this.departure = departure;
        this.destination = destination;
        this.availableSeats = availableSeats;
        this.dateTimeOfDeparture = dateTimeOfDeparture;
    }

    public Flight() {

    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public long getDateTimeOfDeparture() {
        return dateTimeOfDeparture;
    }

    public void setDateTimeOfDeparture(String dateTimeOfDeparture) {
        LocalDateTime localDate = LocalDate.parse(dateTimeOfDeparture, formatter).atStartOfDay();
        this.dateTimeOfDeparture = localDate.toEpochSecond(ZoneOffset.UTC) * 1000;
    }

    @Override
    public String toString() {
        StringBuilder outcome = new StringBuilder("Flight {");

        LocalDateTime localDate = LocalDateTime.ofInstant(Instant.ofEpochMilli(this.dateTimeOfDeparture), ZoneOffset.UTC);
        String formattedDate = localDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        outcome.append("id= ")
                .append(getId())
                .append(", departure= ")
                .append(departure)
                .append(", destination= ")
                .append(destination)
                .append(", departure time: ")
                .append(formattedDate)
                .append('}');

        return outcome.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return Objects.equals(getId(), flight.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(departure, destination, availableSeats, dateTimeOfDeparture, formatter, getId());
    }
}
