package collection;

import entity.Flight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CollectionFlightDaoTest {
    CollectionFlightDao flightDao = new CollectionFlightDao(new ArrayList<>(), "FlightsTest.bin");

    @BeforeEach
    void setup() {
        Flight flight = new Flight();
        flight.setDestination("Ternopil");
        flight.setAvailableSeats(50);
        flight.setDateTimeOfDeparture("29/04/2024, 15:00");
        flightDao.create(flight);
    }

    @Test
    public void getAll() {
        List<Flight> flightList = flightDao.getAll();
        assertEquals(1, flightList.size());

    }


    @Test
    public void create() {
        Flight flight = new Flight();
        flight.setDestination("Uzhgorod");
        flight.setAvailableSeats(20);
        flight.setDateTimeOfDeparture("30/04/2024, 15:00");
        flightDao.create(flight);

        List<Flight> flightList = flightDao.getAll();
        assertEquals(2, flightList.size());
    }

    @Test
    public void getById() {
        Flight flight = flightDao.getById(1);
        assertEquals(1, flight.getId());
    }

    @Test
    public void deleteEntity() {
        flightDao.deleteEntity(1);
        List<Flight> flights = flightDao.getAll();
        assertEquals(0, flights.size());
    }
}
