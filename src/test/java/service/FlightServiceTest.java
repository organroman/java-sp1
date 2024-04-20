package service;

import collection.CollectionFlightDao;
import entity.Flight;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FlightServiceTest {
    private CollectionFlightDao flightDao = new CollectionFlightDao(new ArrayList<>(), "Flights.bin");
    private FlightService flightService = new FlightService(flightDao);

    @Test
    public void getAllFlights() throws IOException, ClassNotFoundException {
        flightDao.loadDataBase();
        List<Flight> flights = flightService.getAllFlights();
        assertEquals(154, flights.size());

    }

    @Test
    public void createNewFlight() throws IOException, ClassNotFoundException {
        flightDao.loadDataBase();
        flightService.createNewFlight("Monaco", 20, "25/04/2024, 15:20");
        List<Flight> flights = flightService.getAllFlights();
        flightService.getAllFlights();
        assertEquals(155, flights.size());

    }

    @Test
    public void getFlight() throws IOException, ClassNotFoundException {
        flightDao.loadDataBase();
        Flight flight = flightService.getFlight(2);
        assertEquals(2, flight.getId());
    }

    @Test
    public void updateFlight() throws IOException, ClassNotFoundException {
        flightDao.loadDataBase();
        Flight flight = flightService.getFlight(2);
        flight.setDestination("Monaco");
        flightService.updateFlight(flight);
        Flight updatedFlight = flightService.getFlight(2);
        assertEquals("Monaco", updatedFlight.getDestination());
    }

    @Test
    public void deleteFlight() throws IOException, ClassNotFoundException {
        flightDao.loadDataBase();
        flightService.deleteFlight(2);
        List<Flight> flights = flightService.getAllFlights();
        assertEquals(153, flights.size());
    }

    @Test
    public void getTimeOfDeparture() throws IOException, ClassNotFoundException {
        flightDao.loadDataBase();
        String time = flightService.getTimeOfDeparture(2);
        assertEquals("04:24", time);
    }

    @Test
    public void getDateOfDeparture() throws IOException, ClassNotFoundException {
        flightDao.loadDataBase();
        String date = flightService.getDateOfDeparture(2);
        assertEquals("19.04.2024", date);
    }

    @Test
    public void findFlights() throws IOException, ClassNotFoundException {
        flightDao.loadDataBase();
        List<Flight> flights = flightService.findFlights("Kharkiv", "19/04/2024", 2);
        assertEquals(1, flights.size());
    }

    @Test
    public void getDestination() throws IOException, ClassNotFoundException {
        flightDao.loadDataBase();
        String destination = flightService.getDestination(2);
        assertEquals("Kharkiv", destination);
    }

    @Test
    public void getAvailableSeats() throws IOException, ClassNotFoundException {
        flightDao.loadDataBase();
        int seats = flightService.getAvailableSeats(2);
        assertEquals(99, seats);
    }

    @Test
    public void getDeparture() throws IOException, ClassNotFoundException {
        flightDao.loadDataBase();
        String departure = flightService.getDeparture(2);
        assertEquals("Kyiv", departure);
    }

    @Test
    public void countFlights() throws IOException, ClassNotFoundException {
        flightDao.loadDataBase();
        int count = flightService.countFlights();
        assertEquals(154, count);
    }

    @Test
    public void decreaseAvailableSeats() throws IOException, ClassNotFoundException {
        flightDao.loadDataBase();
        flightService.decreaseAvailableSeats(2, 2);
        Flight flight = flightService.getFlight(2);
        assertEquals(97, flight.getAvailableSeats());
    }

    @Test
    public void increaseAvailableSeats() throws IOException, ClassNotFoundException {
        flightDao.loadDataBase();
        flightService.increaseAvailableSeats(1, 6);
        Flight flight = flightService.getFlight(1);
        assertEquals(30, flight.getAvailableSeats());
    }

}

