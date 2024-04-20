package collection;

import dao.Dao;
import entity.Flight;
import exception.CollectionDaoException;

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
        try {
            this.flights.add(flight);
        } catch (Exception e) {
            throw new CollectionDaoException(CollectionDaoException.ERROR_CREATING_NEW_FLIGHT);
        }
    }

    @Override
    public List<Flight> getAll() {
        try {
            return flights;
        } catch (Exception e) {
            throw new CollectionDaoException(CollectionDaoException.ERROR_RETRIEVING_ALL_FLIGHTS);
        }
    }

    @Override
    public Flight getById(int id) {
        try {
            List<Flight> flights = getAll();
            Optional<Flight> optionalFlight = flights.stream().filter(item -> item.getId() == id).findFirst();
            return optionalFlight.orElse(null);
        } catch (Exception e) {
            throw new CollectionDaoException(CollectionDaoException.ERROR_GETTING_FLIGHT);
        }
    }

    @Override
    public boolean deleteEntity(int id) {
        try {
            Flight flight = getById(id);
            if (flight == null) {
                return false;
            }

            this.flights.remove(flight);
            return true;
        } catch (Exception e) {
            throw new CollectionDaoException(CollectionDaoException.ERROR_DELETING_FLIGHT);
        }
    }

    @Override
    public boolean updateEntity(Flight y) {
        try {
            if (flights.contains(y)) {
                this.flights.set(flights.indexOf(y), y);
                return true;
            }
            return false;
        } catch (Exception e) {
            throw new CollectionDaoException(CollectionDaoException.ERROR_UPDATING_FLIGHT);
        }
    }

    @Override
    public void loadDataBase() {
        try {
            if (!file.exists()) {
                flights = new ArrayList<>();
            }
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                List<Flight> loadedFlights = (List<Flight>) ois.readObject();
                this.flights.clear();
                this.flights.addAll(loadedFlights);
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new CollectionDaoException(CollectionDaoException.ERROR_LOADING_DATA, e);
        }
    }

    @Override
    public void saveDataBase() {

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(this.flights);
        } catch (Exception e) {
            throw new CollectionDaoException(CollectionDaoException.ERROR_SAVING_DATA, e);
        }
    }
}
