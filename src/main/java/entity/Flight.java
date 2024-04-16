package entity;

public class Flight extends AbstractEntity{
    String departure;
    String destination;
    int availableSeats;
    long dateTimeOfDeparture;

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

    public void setDateTimeOfDeparture(long dateTimeOfDeparture) {
        this.dateTimeOfDeparture = dateTimeOfDeparture;
    }
}
