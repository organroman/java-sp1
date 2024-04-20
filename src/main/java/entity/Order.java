package entity;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

public class Order implements Serializable {

    private Flight flight;

    private Person buyer;

    private int amount;

    List<Person> passengers;

    int orderId;

    public Order(Flight flight, Person buyer, int amount, List<Person> passengers) {
        Random random = new Random();
        this.orderId = random.nextInt(9999);

        this.flight = flight;
        this.buyer = buyer;
        this.amount = amount;
        this.passengers = passengers;
    }

    @Override
    public String toString() {
        LocalDateTime localDate = LocalDateTime.ofInstant(Instant.ofEpochMilli(flight.dateTimeOfDeparture), ZoneOffset.UTC);
        String formattedDate = localDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy, HH:mm"));
        StringBuilder sb = new StringBuilder();
        sb.append("\nOrderId= ").append(orderId).append(" | ")
                .append("Buyer: ").append(buyer).append(" | ")
                .append("amount seats= ").append(amount).append(" | ")
                .append("passengers= ").append(passengers)
                .append("\nTicket on board: {")
                .append("flight id= ").append(flight.id).append(" | ")
                .append("departure= ").append(flight.departure).append(" | ")
                .append("destination= ").append(flight.destination).append(" | ")
                .append("departure time: ").append(formattedDate).append("}");
        return String.valueOf(sb);
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public Person getBuyer() {
        return buyer;
    }

    public void setBuyer(Person buyer) {
        this.buyer = buyer;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public List<Person> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Person> passengers) {
        this.passengers = passengers;
    }
}
