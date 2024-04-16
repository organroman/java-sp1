package collection;

import dao.Dao;
import entity.Order;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CollectionOrderDao implements Dao<Order> {

    private List<Order> orders = new ArrayList<>();
    private final File file = new File("Orders.bin");

    @Override
    public void create(Order y) {
        orders.add(y);
    }

    @Override
    public List<Order> getAll() {
        return orders;
    }


    @Override
    public Optional<Order> getById(int index) {
        return Optional.ofNullable(orders.get(index));
    }

    @Override
    public boolean deleteEntity(int id) {
        Optional<Order> order = getById(id);
        if (!orders.contains(order)) {
            return false;
        }
        this.orders.remove(order);
        return true;
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
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Object object = ois.readObject();
            orders = (ArrayList<Order>) object;
            ois.close();
            if (!orders.isEmpty())
                System.out.println("Add DataBase From file Successful");
        }
    }

    @Override
    public void saveDataBase(List<Order> object) throws IOException {
        try {
            final ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(object);
            oos.close();
        } catch (
                FileNotFoundException ex) {
            System.out.println("File database.bin not found ");
        }
    }
}