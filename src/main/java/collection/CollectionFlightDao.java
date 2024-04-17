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

    private int id = 0;

    public CollectionFlightDao(List<Flight> flights, String filename) {
        this.flights = flights;
        this.file = new File(filename);
    }

    @Override
    public void create(Flight flight) {
        if (this.flights.contains(flight)) {
            updateEntity(flight);
        } else {
            flight.setId(++id);
            this.flights.add(flight);
        }
    }

    @Override
    public List<Flight> getAll() {
        return flights;
    }

    @Override
    public boolean deleteEntity(int id) {
        Optional<Flight> flightOptional = getById(id);
        if (flightOptional.isPresent()) {
            Flight flight = flightOptional.get();
            this.flights.remove(flight);
            return true;
        }

        return false;
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
