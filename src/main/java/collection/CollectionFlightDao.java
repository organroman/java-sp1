package collection;

import dao.Dao;
import entity.Flight;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CollectionFlightDao implements Dao<Flight> {
    private List<Flight> flights;
    private File file;


    public CollectionFlightDao(List<Flight> flights, String filename) {
        this.flights = flights;
        this.file = new File(filename);
    }

    @Override
    public void create(Flight flight) {

        if (this.flights.contains(flight)) {
            updateEntity(flight);
        } else {

            this.flights.add(flight);
        }
    }

    @Override
    public List<Flight> getAll() {
        return flights;
    }

    @Override
    public Flight getById(int id) {
        List<Flight> flights = getAll();
        Optional<Flight> optionalFlight = flights.stream().filter(item -> item.getId() == id).findFirst();
        return optionalFlight.orElse(null);
    }

    @Override
    public boolean deleteEntity(int id) {
        Flight flight = getById(id);

        if (flight == null) return false;
        this.flights.remove(flight);
        return true;

    }

    @Override
    public boolean updateEntity(Flight y) {
        if (flights.contains(y)) {
            this.flights.set(flights.indexOf(y), y);
            return true;
        }
        return false;
    }

    @Override
    public void loadDataBase() throws IOException, ClassNotFoundException {
        if (!file.exists()) {
            flights = new ArrayList<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            List<Flight> loadedFlights = (List<Flight>) ois.readObject();
            this.flights.clear();
            this.flights.addAll(loadedFlights);
        }
    }

    @Override
    public void saveDataBase() throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(this.flights);

        }
    }
}
