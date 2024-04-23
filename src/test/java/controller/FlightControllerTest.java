package controller;

import entity.Flight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FlightControllerTest {
    private final FlightController flightController = new FlightController();

    @BeforeEach void loadData() throws IOException, ClassNotFoundException {
        flightController.loadData();
    }

    @Test
    public void getAllFlights() {
        List<Flight> flights = flightController.getAllFlights();
        assertEquals(155, flights.size());

    }

    @Test
    public void getFlight() throws IOException {
        Flight flight = flightController.getFlight(2);
        assertEquals(2, flight.getId());
    }

    @Test
    public void updateFlight() throws IOException, ClassNotFoundException {
        Flight flight = flightController.getFlight(2);
        flight.setDestination("Monaco");
        flightController.updateFlight(flight);
        Flight updatedFlight = flightController.getFlight(2);
        assertEquals("Monaco", updatedFlight.getDestination());
    }

    @Test
    public void createFlight() throws IOException, ClassNotFoundException {
       Flight flight =  flightController.createFlight("New York", 25, "29/04/2024, 20:50");
        List<Flight> flights = flightController.getAllFlights();
        assertEquals(156, flights.size());

    }

    @Test
    public void getTimeOfDeparture() throws IOException, ClassNotFoundException {
        String time = flightController.getTimeOfDeparture(2);
        assertEquals("04:24", time);
    }

    @Test
    public void getDateOfDeparture() throws IOException, ClassNotFoundException {
        String date = flightController.getDateOfDeparture(2);
        assertEquals("19.04.2024", date);
    }

    @Test
    public void findFlights() throws IOException, ClassNotFoundException {
        List<Flight> flights = flightController.findFlights("Kharkiv", "19/04/2024", 2);
        assertEquals(1, flights.size());
    }

    @Test
    public void getDestination() throws IOException, ClassNotFoundException {
        String destination = flightController.getDestination(2);
        assertEquals("Kharkiv", destination);
    }

    @Test
    public void getAvailableSeats() throws IOException, ClassNotFoundException {
        int seats = flightController.getAvailableSeats(2);
        assertEquals(99, seats);
    }

    @Test
    public void getDeparture() throws IOException, ClassNotFoundException {
        String departure = flightController.getDeparture(2);
        assertEquals("Kyiv", departure);
    }

    @Test
    public void countFlights() throws IOException, ClassNotFoundException {
        int count = flightController.countFlights();
        assertEquals(155, count);
    }

    @Test
    public void decreaseAvailableSeats() throws IOException, ClassNotFoundException {

        flightController.decreaseAvailableSeats(2, 2);
        Flight flight = flightController.getFlight(2);
        assertEquals(97, flight.getAvailableSeats());
    }

    @Test
    public void increaseAvailableSeats() throws IOException, ClassNotFoundException {
        flightController.increaseAvailableSeats(1, 6);
        Flight flight = flightController.getFlight(1);
        assertEquals(30, flight.getAvailableSeats());
    }


}
