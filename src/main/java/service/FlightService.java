package service;

import dao.Dao;
import entity.Flight;
import util.Helpers;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class FlightService {
    private final Dao<Flight> flightDao;

    public FlightService(Dao<Flight> flightDao) {
        this.flightDao = flightDao;
    }

    public List<Flight> getAllFlights() {
        return flightDao.getAll();
    }

    public void displayAllFlights() {
        List<Flight> flights = flightDao.getAll();
        flights.forEach(System.out::println);
    }

    public Flight createNewFlight(String destination, int seats, String dateTime) throws IOException {
        List<Flight> flights = getAllFlights();
        int nextId = flights.stream()
                .mapToInt(Flight::getId)
                .max()
                .orElse(0) + 1;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy, HH:mm");
        LocalDateTime localDateTime = LocalDateTime.parse(dateTime, formatter);
        long dateTimeMils = localDateTime.toEpochSecond(ZoneOffset.UTC) * 1000;
        Flight newFlight = new Flight(destination, seats, dateTimeMils);
        newFlight.setId(nextId);

        flightDao.create(newFlight);
//        flightDao.saveDataBase();

        return newFlight;
    }

    public Flight getFlight(int id) {
        return flightDao.getById(id);
    }

    public void updateFlight(Flight flight) {
        flightDao.updateEntity(flight);
    }

    public void deleteFlight(int id) {
        flightDao.deleteEntity(id);
    }

    public String getTimeOfDeparture(int id) {
        Flight flight = flightDao.getById(id);
        if (flight != null) {
            long dateTimeOfDepartureMillis = flight.getDateTimeOfDeparture();
            LocalDateTime departureDateTime = Instant.ofEpochMilli(dateTimeOfDepartureMillis)
                    .atZone(ZoneOffset.UTC)
                    .toLocalDateTime();
            return departureDateTime.format(DateTimeFormatter.ofPattern("HH:mm"));
        } else return "Flight not found";

    }

    public String getDateOfDeparture(int id) {
        Flight flight = flightDao.getById(id);
        if (flight != null) {
            long dateTimeOfDepartureMillis = flight.getDateTimeOfDeparture();
            LocalDateTime departureDateTime = Instant.ofEpochMilli(dateTimeOfDepartureMillis)
                    .atZone(ZoneOffset.UTC)
                    .toLocalDateTime();


            return departureDateTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        } else return "Flight not found";
    }

    public List<Flight> findFlights(String destination, String date, int passengers) {
        List<Flight> allFlights = getAllFlights();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate searchDate = LocalDate.parse(date, formatter);

        return allFlights.stream()
                .filter(flight -> Objects.equals(flight.getDestination(), destination))
                .filter(flight -> Helpers.isSameDay(flight.getDateTimeOfDeparture(), searchDate))
                .filter(flight -> flight.getAvailableSeats() >= passengers)
                .collect(Collectors.toList());
    }

    public String getDestination(int id) {
        Flight flight = flightDao.getById(id);
        if (flight != null) {
            return flight.getDestination();
        } else return "Flight not found";
    }

    public int getAvailableSeats(int id) throws IOException {
        Flight flight = flightDao.getById(id);

        if (flight != null) {
            return flight.getAvailableSeats();
        } else throw new IOException("Flight not found");

    }

    public String getDeparture(int id) throws IOException {
        Flight flight = flightDao.getById(id);
        if (flight != null) {
            return flight.getDeparture();
        } else throw new IOException("Flight not found");
    }


    public int countFlights() {
        return flightDao.getAll().size();

    }

    public List<Flight> getTodayFlights() {
        LocalDate today = LocalDate.now();
        List<Flight> flights = flightDao.getAll();
        return flights.stream()
                .filter(flight -> Helpers.isSameDay(flight.getDateTimeOfDeparture(), today))
                .collect(Collectors.toList());
    }

    public boolean decreaseAvailableSeats(int id, int seatsNumber) throws IOException {
        Flight flight = getFlight(id);
        int availableSeats = getAvailableSeats(id);

        if (availableSeats > 0 && availableSeats > seatsNumber) {
            flight.setAvailableSeats(availableSeats - seatsNumber);
            return true;
        } else return false;
    }

    public boolean increaseAvailableSeats(int id, int seatsNumber) throws IOException {
        Flight flight = getFlight(id);
        int availableSeats = getAvailableSeats(id);

        flight.setAvailableSeats(availableSeats + seatsNumber);
        return true;

    }

    public void saveData() throws IOException {
        flightDao.saveDataBase();
    }

    public void loadData() throws IOException, ClassNotFoundException {
        flightDao.loadDataBase();

    }


}
