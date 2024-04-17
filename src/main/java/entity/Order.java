package entity;

import java.util.List;
import java.util.Random;

public class Order extends AbstractEntity {
    private int orderId;
    private Flight flight;

    private Person person;

    private int amount;

    List<Person> passengers;

    public Order(Flight flight, Person person, int amount, List<Person> passengers) {
        Random random = new Random();
        this.orderId = random.nextInt(9999);
        this.flight = flight;
        this.person = person;
        this.amount = amount;
        this.passengers = passengers;
    }

    @Override
    public String toString() {
        return "OrderId=" + orderId +
                ", flight=" + flight +
                ", person=" + person +
                ", amount=" + amount +
                ", passengers=" + passengers;
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

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
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
