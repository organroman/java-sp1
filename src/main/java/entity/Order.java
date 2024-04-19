package entity;

import java.io.Serializable;
import java.util.List;
import java.util.Random;

public class Order extends AbstractEntity implements Serializable {

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
        return "\nOrderId= " + orderId +
                ", Buyer: " + buyer +
                ", amount= " + amount +
                ", passengers=" + passengers +
                ", \nflight=" + flight;
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
