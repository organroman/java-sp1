package collection;

import dao.Dao;
import entity.Order;
import exception.CollectionDaoException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CollectionOrderDao implements Dao<Order> {

    private List<Order> orders;
    private final File file;

    public CollectionOrderDao(List<Order> orders, String fileName) {
        this.orders = orders;
        this.file = new File(fileName);
    }

    @Override

    public void create(Order y) {
        try {
            orders.add(y);
        } catch (Exception e) {
            throw new CollectionDaoException(CollectionDaoException.ERROR_CREATING_NEW_ORDER);
        }

    }

    @Override
    public List<Order> getAll() {
        try {
            return orders;
        } catch (Exception e) {
            throw new CollectionDaoException(CollectionDaoException.ERROR_GET_ALL_ORDERS);
        }
    }

    @Override
    public Order getById(int id) {
        try {
            Optional<Order> order = orders.stream().filter(item -> item.getOrderId() == id).findFirst();
            return order.orElse(null);
        } catch (Exception e) {
            throw new CollectionDaoException(CollectionDaoException.ERROR_GETTING_ORDER);
        }

    }


    @Override
    public boolean deleteEntity(int orderId) {
        try {
            Optional<Order> toDelete = orders.stream().filter(order -> order.getOrderId() == orderId).findFirst();
            if (toDelete.isEmpty()) return false;
            orders.remove(toDelete.get());
            return true;
        } catch (Exception e) {
            throw new CollectionDaoException(CollectionDaoException.ERROR_DELETE_ORDER);
        }

    }

    @Override
    public boolean updateEntity(Order y) {
        try {
            if (null == getById(y.getOrderId())) return false;
            Order orderToUpdate = orders.stream().filter(order -> order.getOrderId() == y.getOrderId()).findFirst().get();
            this.orders.set(orders.indexOf(orderToUpdate), y);
            return true;
        } catch (Exception e) {
            throw new CollectionDaoException(CollectionDaoException.ERROR_UPDATING_ORDER);
        }
    }

    @Override
    public void loadDataBase() {
        try {
            if (!file.exists())
                orders = new ArrayList<>();
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                Object object = ois.readObject();
                List<Order> newOrders = (ArrayList<Order>) object;
                orders.clear();
                orders.addAll(newOrders);
                ois.close();
                if (!orders.isEmpty())
                    System.out.println("Add DataBase From file Successful");
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new CollectionDaoException(CollectionDaoException.ERROR_LOADING_DATA_ORDERS, e);
        }

    }

    @Override
    public void saveDataBase() {
        try {
            final ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(orders);
            oos.close();
        } catch (IOException e) {
            throw new CollectionDaoException(CollectionDaoException.ERROR_SAVING_DATA_ORDERS, e);
        }
    }
}