package controller;

import collection.CollectionOrderDao;
import entity.Flight;
import entity.Order;
import entity.Person;
import service.OrderService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class OrderController {
    OrderService orderService = new OrderService(new CollectionOrderDao(new ArrayList<>(), "Orders.bin"));


    public void create(Flight flight, Person person, int n, List<Person> list) {
        orderService.create(new Order(flight, person, n, list));
    }

    public void deleteById(int id) {
        orderService.deleteById(id);
    }

    public List<Order> getMyOrders(Person person) {
        return orderService.getMyOrders(person);
    }

    public void saveDataBase() throws IOException {
        orderService.saveDataBase();
    }


    public void printBase() {
        orderService.printOrderList();
    }

    public void loadData() throws IOException, ClassNotFoundException {
        orderService.loadData();
    }
}
