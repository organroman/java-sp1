package collection;

import entity.Flight;
import entity.Order;
import entity.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class CollectionOrderDaoTest {
    CollectionOrderDao orders = new CollectionOrderDao(new ArrayList<>(), "Orders.bin");

    @BeforeEach
    void setup() {
        Person person = new Person("AAA", "BBB");
        Order order = new Order(new Flight(), person, 1, new ArrayList<>());
        orders.create(order);
    }

    @Test
    public void createTest() {
        Order order = new Order(new Flight(), new Person("CCC", "DDD"), 1, new ArrayList<>());
        orders.create(order);
        assertEquals(2, orders.getAll().size());
    }

    @Test
    public void getAllTest() {
        assertEquals(1, orders.getAll().size());
    }

    @Test
    public void getByIdTest() {
        int i = orders.getAll().stream().findFirst().get().getOrderId();
        assertEquals(i, orders.getById(i).getOrderId());
    }


    @Test
    public void deleteEntityTest() {
        int i = orders.getAll().stream().findFirst().get().getOrderId();
        orders.deleteEntity(i);
        assertEquals(0, orders.getAll().size());
    }

    @Test
    public void updateEntityTest() {
        Person person1 = new Person("CCC", "DDD");
        Order order1 = new Order(new Flight(), person1, 10, new ArrayList<>());
        order1.setOrderId(orders.getAll().get(0).getOrderId());
        orders.updateEntity(order1);
        assertEquals(10, orders.getAll().get(0).getAmount());
    }

}
