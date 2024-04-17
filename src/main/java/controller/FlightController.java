package controller;

import collection.CollectionFlightDao;
import entity.Flight;
import service.FlightService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FlightController {
    private final FlightService flightService = new FlightService(new CollectionFlightDao(new ArrayList<>(), "Flights.bin"));


    public List<Flight> getAllFlights() {
        return flightService.getAllFlights();
    }

    public void  displayAllFlights() {
        flightService.displayAllFlights();
    }

    public Flight createFlight() throws IOException {
        return flightService.createNewFlight();
    }

    public Flight getFlight(int id) {
        return flightService.getFlight(id);
    }

    public void  updateFlight(Flight flight) {
        flightService.updateFlight(flight);
    }

    public void deleteFlight(int id)  {
        flightService.deleteFlight(id);
    }

    public String getDateOfDeparture(int id) {
        return flightService.getDateOfDeparture(id);
    }

    public String getTimeOfDeparture(int id) {
        return flightService.getTimeOfDeparture(id);
    }

    public String getDestination(int id) {
        return flightService.getDestination(id);
    }

    public int getAvailableSeats(int id) throws IOException {
        return flightService.getAvailableSeats(id);
    }

    public String getDeparture(int id)throws IOException{
        return flightService.getDeparture(id);
    }

    public int countFlights() {
        return flightService.countFlights();
    }

    public List<Flight> getTodayFLights() {
        return flightService.getTodayFlights();
    }

    public boolean decreaseAvailableSeats(int id, int seats) throws IOException {
        return flightService.decreaseAvailableSeats(id, seats);
    }

    public void saveData() throws IOException {
        flightService.saveData();
    }

    public void loadData() throws IOException, ClassNotFoundException {
        flightService.loadData();
    }

}