package service;

import dao.Dao;
import entity.Order;
import entity.Person;
import exception.OrderServiceException;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class OrderService {
    private final Dao<Order> orderDao;


    public OrderService(Dao<Order> orderDao) {
        this.orderDao = orderDao;
    }

    public void create(Order order) {
        try {
            orderDao.create(order);
        } catch (Exception e) {
            throw new OrderServiceException(OrderServiceException.ERROR_CREATING_NEW_ORDER);
        }
    }

    public void deleteById(int id) {
        try {
            if (orderDao.deleteEntity(id)) System.out.println("Order Was deleted");
            else System.out.println("Order with this ID not found");
        } catch (Exception e) {
            throw new OrderServiceException(OrderServiceException.ERROR_DELETE_ORDER);
        }
    }

    public List<Order> getMyOrders(Person person) {
        try {
            return orderDao.getAll().stream().filter(order -> (order.getBuyer().equals(person) || (order.getPassengers().contains(person))))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new OrderServiceException(OrderServiceException.ERROR_GET_MY_ORDERS);
        }
    }

    public void saveDataBase() {
        try {
            orderDao.saveDataBase();
        } catch (IOException e) {
            throw new OrderServiceException(OrderServiceException.ERROR_SAVING_DATA_ORDERS, e);
        }
    }


    public void printOrderList() {
        try {
            System.out.println(orderDao.getAll());
        } catch (Exception e) {
            throw new OrderServiceException(OrderServiceException.ERROR_DISPLAYING_ALL_ORDER);
        }
    }

    public void loadData() {
        try {
            orderDao.loadDataBase();
        } catch (IOException | ClassNotFoundException e) {
            throw new OrderServiceException(OrderServiceException.ERROR_LOADING_DATA_ORDERS, e);
        }
    }

    public Order getById(int id) {
        try {
            return orderDao.getById(id);
        } catch (Exception e) {
            throw new OrderServiceException(OrderServiceException.ERROR_GET_ORDER);
        }
    }

    public void updateOrder(Order order){
        try {
            if (orderDao.updateEntity(order)) System.out.println("Order Was update");
            else System.out.println("Order with this ID not found");
        } catch (Exception e) {
            throw new OrderServiceException(OrderServiceException.ERROR_UPDATING_ORDER);
        }

    }
}



