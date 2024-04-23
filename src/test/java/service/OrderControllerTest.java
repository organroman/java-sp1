package service;

import collection.CollectionFlightDao;

import controller.OrderController;
import entity.Flight;
import entity.Order;

import entity.Person;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class OrderControllerTest {
    private CollectionFlightDao flightDao = new CollectionFlightDao(new ArrayList<>(), "Flights.bin");

    private OrderController orderController = new OrderController();

    @Test
    public void loadDataTest() throws IOException, ClassNotFoundException {
        orderController.loadData();
        List<Order> order = orderController.getMyOrders(new Person("Leo", "Cat"));
        assertNotEquals(0, order.size());

    }

    @Test
    public void createTest() {
        Person person = new Person("AAA", "BBB");
        orderController.create(new Flight(), person, 1, new ArrayList<>());
        assertNotEquals(0, orderController.getMyOrders(person).size());
    }

    @Test
    public void deleteByIdTest() {
        Person person = new Person("AAA", "BBB");
        orderController.create(new Flight(), person, 1, new ArrayList<>());
        Optional<Order> orderOptional = orderController.getMyOrders(person).stream().findFirst();
        orderController.deleteById(orderOptional.get().getOrderId());
        assertTrue(orderController.getMyOrders(person).isEmpty());
    }

    @Test
    public void getMyOrdersTest() {
        Person person = new Person("AAA", "BBB");
        orderController.create(new Flight(), person, 1, new ArrayList<>());
        orderController.create(new Flight(), new Person("CCC", "DDD"), 1, new ArrayList<>(Arrays.asList(person)));
        assertAll("Check getMyOrdersTest =================================================",
                () -> assertEquals(person, orderController.getMyOrders(person).get(0).getBuyer()),
                () -> assertEquals(2, orderController.getMyOrders(person).size()));
        assertEquals(person, orderController.getMyOrders(person).get(0).getBuyer());
    }

    @Test
    public void getById() {
        Person person = new Person("AAA", "BBB");
        orderController.create(new Flight(), person, 1, new ArrayList<>());
        int id = orderController.getMyOrders(person).get(0).getOrderId();
        assertEquals(id, orderController.getById(id).getOrderId());
    }

    @Test
    public void updateOrder() throws IOException, ClassNotFoundException {
        flightDao.loadDataBase();
        Person person = new Person("AAA", "BBB");
        Person person1 = new Person("CCC", "DDD");
        orderController.create(flightDao.getById(1), person, 1, new ArrayList<>());
        Order order = orderController.getMyOrders(person).get(0);
        Order order1 = new Order(flightDao.getById(2), person1, 10, new ArrayList<>());
        order1.setOrderId(order.getOrderId());
        orderController.updateOrder(order1);
        assertEquals(10, orderController.getMyOrders(person1).get(0).getAmount());
    }
}
