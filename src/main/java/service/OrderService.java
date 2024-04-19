package service;

import dao.Dao;
import entity.Order;
import entity.Person;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class OrderService {
    private final Dao<Order> orderDao;


    public OrderService(Dao<Order> orderDao) {
        this.orderDao = orderDao;
    }

    public void create(Order order) {
        orderDao.create(order);
    }

    public void deleteById(int id) {
        if (orderDao.deleteEntity(id)) System.out.println("Order Was deleted");
        else System.out.println("Order with this ID not found");
    }

    public List<Order> getMyOrders(Person person) {
        return orderDao.getAll().stream().filter(order -> (order.getBuyer().equals(person) || (order.getPassengers().contains(person))))
                .collect(Collectors.toList());
    }

    public void saveDataBase() throws IOException {
        orderDao.saveDataBase();
    }


    public void printOrderList() {
        System.out.println(orderDao.getAll());
    }

    public void loadData() throws IOException, ClassNotFoundException {
        orderDao.loadDataBase();
    }
}



