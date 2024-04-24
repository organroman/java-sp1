package service;

import collection.CollectionFlightDao;
import collection.CollectionOrderDao;
import entity.Order;
import entity.Person;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class OrderServiceTest {
    private CollectionFlightDao flightDao = new CollectionFlightDao(new ArrayList<>(), "Flights.bin");
    private CollectionOrderDao orderDao = new CollectionOrderDao(new ArrayList<>(), "Orders.bin");
    private OrderService ordersService = new OrderService(orderDao);

    @Test
    public void createTest() {
        orderDao.loadDataBase();
        flightDao.loadDataBase();
        Order order = new Order(flightDao.getById(2), new Person("TestName", "TestSurname"), 2, new ArrayList<>(Arrays.asList(new Person("TestName2", "Butter"))));
        int i = order.getOrderId();
        int size = orderDao.getAll().size();
        ordersService.create(order);
        assertAll("Check Create new Order=================================================",
                () -> assertEquals(size + 1, orderDao.getAll().size()),
                () -> assertEquals(orderDao.getById(i), order));
    }


    @Test
    public void deleteById() {
        orderDao.loadDataBase();
        Optional<Order> orderOptional = orderDao.getAll().stream().findFirst();
        Order order = orderOptional.get();
        int i = order.getOrderId();
        int size = orderDao.getAll().size();
        ordersService.deleteById(i);
        assertAll("Check Delete Order=================================================",
                () -> assertEquals(size - 1, orderDao.getAll().size()),
                () -> assertFalse(orderDao.getAll().contains(order)));
    }

    @Test

    public void getMyOrders() {
        orderDao.loadDataBase();
        Person person = new Person("TestName", "TestSurname");
        Order orderTest = new Order(flightDao.getById(2), person, 2, new ArrayList<>(Arrays.asList(new Person("TestName2", "Butter"))));
        ordersService.create(orderTest);
        assertAll("Check getMyOrders =================================================",
                () -> assertEquals(orderTest, ordersService.getMyOrders(person).get(0)),
                () -> assertEquals(1, ordersService.getMyOrders(person).size()));
    }

    @Test
    public void loadData() {
        ordersService.loadData();
        assertNotEquals(0, orderDao.getAll().size());
    }

    @Test
    public void getById() {
        ordersService.loadData();
        Order order = new Order(flightDao.getById(2), new Person("TestName", "TestSurname"), 2, new ArrayList<>(Arrays.asList(new Person("TestName2", "Butter"))));
        int i = order.getOrderId();
        ordersService.create(order);
        assertEquals(order, ordersService.getById(i));
    }

    @Test
    public void updateOrder() {
        ordersService.loadData();
        flightDao.loadDataBase();
        Person person = new Person("AAA", "BBB");
        Person person1 = new Person("CCC", "DDD");
        Order order = new Order(flightDao.getById(1), person, 1, new ArrayList<>());
        ordersService.create(order);
        Order order1 = new Order(flightDao.getById(2), person1, 10, new ArrayList<>());
        order1.setOrderId(order.getOrderId());
        ordersService.updateOrder(order1);
        assertEquals(10, ordersService.getMyOrders(person1).get(0).getAmount());
    }
}
