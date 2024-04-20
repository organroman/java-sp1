package collection;

import dao.Dao;
import entity.Order;

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
        orders.add(y);
    }

    @Override
    public List<Order> getAll() {
        return orders;
    }

    @Override
    public Order getById(int id) {
        Optional<Order> order = orders.stream().filter(item -> item.getOrderId() == id).findFirst();
        return order.orElse(null);

    }


    @Override
    public boolean deleteEntity(int orderId) {
        Optional<Order> toDelete = orders.stream().filter(order -> orderId == order.getOrderId()).findFirst();
        if (toDelete.isPresent()) {
            orders.remove(toDelete.get());
            System.out.println("Order with id= " + orderId + " was delete");
            return true;
        }
        return false;
    }

    @Override
    public boolean updateEntity(Order y) {
        if (orders.contains(y)) {
            this.orders.set(orders.indexOf(y), y);
            return true;
        }
        return false;
    }

    @Override
    public void loadDataBase() throws IOException, ClassNotFoundException {
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

    }

    @Override
    public void saveDataBase() throws IOException {
        try {
            final ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(orders);
            oos.close();
        } catch (
                FileNotFoundException ex) {
            System.out.println("File database.bin not found ");
        }
    }
}