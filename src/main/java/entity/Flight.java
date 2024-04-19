package entity;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Flight implements Serializable {
    String departure;
    String destination;
    int availableSeats;
    long dateTimeOfDeparture;
    int id;

    private static int nextId = 1;

    private transient DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy, HH:mm");

    public Flight(String destination, int availableSeats, long dateTimeOfDeparture) {
        this.id = nextId++;
        this.departure = "Kyiv";
        this.destination = destination;
        this.availableSeats = availableSeats;
        this.dateTimeOfDeparture = dateTimeOfDeparture;
    }

    public Flight() {
        this.id = nextId++;
        this.departure = "Kyiv";

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        if (formatter == null) {
            formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy, HH:mm");
        }
        LocalDateTime localDateTime = LocalDateTime.parse(dateTimeOfDeparture, formatter);
        this.dateTimeOfDeparture = localDateTime.toEpochSecond(ZoneOffset.UTC) * 1000;
    }

    @Override
    public String toString() {
        StringBuilder outcome = new StringBuilder("Flight {");

        LocalDateTime localDate = LocalDateTime.ofInstant(Instant.ofEpochMilli(this.dateTimeOfDeparture), ZoneOffset.UTC);
        String formattedDate = localDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy, HH:mm"));

        outcome.append("id= ")
                .append(getId())
                .append(", departure= ")
                .append(departure)
                .append(", destination= ")
                .append(destination)
                .append(", departure time: ")
                .append(formattedDate)
                .append(", available seats: ")
                .append(availableSeats)
                .append("}");

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
