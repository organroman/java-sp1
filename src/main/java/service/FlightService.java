package service;

import dao.Dao;
import entity.Flight;
import exception.FlightServiceException;
import util.Helpers;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
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
        try {
            return flightDao.getAll();
        } catch (Exception e) {
            throw new FlightServiceException(FlightServiceException.ERROR_RETRIEVING_ALL_FLIGHTS, e);
        }

    }

    public void displayAllFlights() {
        try {
            List<Flight> flights = flightDao.getAll();
            flights.forEach(System.out::println);
        } catch (Exception e) {
            throw new FlightServiceException(FlightServiceException.ERROR_DISPLAYING_ALL_FLIGHTS, e);
        }

    }

    public Flight createNewFlight(String destination, int seats, String dateTime) {
        try {
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
            return newFlight;
        } catch (Exception e) {
            throw new FlightServiceException(FlightServiceException.ERROR_CREATING_NEW_FLIGHT, e);
        }
    }

    public Flight getFlight(int id) {
        try {
            return flightDao.getById(id);
        } catch (Exception e) {
            throw new FlightServiceException(FlightServiceException.ERROR_GETTING_FLIGHT, e);
        }

    }

    public void updateFlight(Flight flight) {
        try {
            flightDao.updateEntity(flight);
        } catch (Exception e) {
            throw new FlightServiceException(FlightServiceException.ERROR_UPDATING_FLIGHT, e);
        }

    }

    public void deleteFlight(int id) {
        try {
            flightDao.deleteEntity(id);
        } catch (Exception e) {
            throw new FlightServiceException(FlightServiceException.ERROR_DELETING_FLIGHT, e);
        }
    }

    public String getTimeOfDeparture(int id) {
        try {
            Flight flight = flightDao.getById(id);
            if (flight != null) {
                long dateTimeOfDepartureMillis = flight.getDateTimeOfDeparture();
                LocalDateTime departureDateTime = Instant.ofEpochMilli(dateTimeOfDepartureMillis)
                        .atZone(ZoneOffset.UTC)
                        .toLocalDateTime();
                return departureDateTime.format(DateTimeFormatter.ofPattern("HH:mm"));
            } else return FlightServiceException.ERROR_FLIGHT_NOT_FOUND;
        } catch (Exception e) {
            throw new FlightServiceException(FlightServiceException.ERROR_GETTING_TIME_FLIGHT, e);
        }

    }

    public String getDateOfDeparture(int id) {
        try {
            Flight flight = flightDao.getById(id);
            if (flight != null) {
                long dateTimeOfDepartureMillis = flight.getDateTimeOfDeparture();
                LocalDateTime departureDateTime = Instant.ofEpochMilli(dateTimeOfDepartureMillis)
                        .atZone(ZoneOffset.UTC)
                        .toLocalDateTime();

                return departureDateTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            } else return FlightServiceException.ERROR_FLIGHT_NOT_FOUND;
        } catch (Exception e) {
            throw new FlightServiceException(FlightServiceException.ERROR_GETTING_DATE_FLIGHT, e);
        }
    }

    public List<Flight> findFlights(String destination, String date, int passengers) {
        try {
            List<Flight> allFlights = getAllFlights();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate searchDate = LocalDate.parse(date, formatter);

            return allFlights.stream()
                    .filter(flight -> Objects.equals(flight.getDestination(), destination))
                    .filter(flight -> Helpers.isSameDay(flight.getDateTimeOfDeparture(), searchDate))
                    .filter(flight -> flight.getAvailableSeats() >= passengers)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new FlightServiceException(FlightServiceException.ERROR_FINDING_FLIGHTS, e);
        }
    }

    public String getDestination(int id) {
        try {
            Flight flight = flightDao.getById(id);
            if (flight != null) {
                return flight.getDestination();
            } else return FlightServiceException.ERROR_FLIGHT_NOT_FOUND;
        } catch (Exception e) {
            throw new FlightServiceException(FlightServiceException.ERROR_GETTING_DESTINATION_FLIGHT, e);
        }
    }

    public int getAvailableSeats(int id) {
        try {
            Flight flight = flightDao.getById(id);

            if (flight != null) {
                return flight.getAvailableSeats();
            } else throw new FlightServiceException(FlightServiceException.ERROR_FLIGHT_NOT_FOUND);
        } catch (Exception e) {
            throw new FlightServiceException(FlightServiceException.ERROR_GETTING_AVAILABLE_SEATS_FLIGHT, e);
        }

    }

    public String getDeparture(int id) {
        try {
            Flight flight = flightDao.getById(id);
            if (flight != null) {
                return flight.getDeparture();
            } else throw new FlightServiceException(FlightServiceException.ERROR_FLIGHT_NOT_FOUND);
        } catch (Exception e) {
            throw new FlightServiceException(FlightServiceException.ERROR_GETTING_DEPARTURE_FLIGHT, e);
        }
    }


    public int countFlights() {
        try {
            return flightDao.getAll().size();
        } catch (Exception e) {
            throw new FlightServiceException(FlightServiceException.ERROR_COUNTING_FLIGHTS, e);
        }
    }

    public List<Flight> getTodayFlights() {
        try {
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime next24Hours = now.plusHours(24);

            List<Flight> flights = flightDao.getAll();

            return flights.stream()
                    .filter(flight -> {
                        LocalDateTime departureDateTime = LocalDateTime.ofInstant(
                                Instant.ofEpochMilli(flight.getDateTimeOfDeparture()),
                                ZoneOffset.UTC
                        );
                        return departureDateTime.isAfter(now) && departureDateTime.isBefore(next24Hours);
                    })
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new FlightServiceException(FlightServiceException.ERROR_GETTING_TODAY_FLIGHTS, e);
        }
    }

    public boolean decreaseAvailableSeats(int id, int seatsNumber) {
        try {
            Flight flight = getFlight(id);
            int availableSeats = getAvailableSeats(id);

            if (availableSeats > 0 && availableSeats > seatsNumber) {
                flight.setAvailableSeats(availableSeats - seatsNumber);
                return true;
            } else return false;
        } catch (Exception e) {
            throw new FlightServiceException(FlightServiceException.ERROR_DECREASING_AVAILABLE_SEATS, e);
        }
    }

    public boolean increaseAvailableSeats(int id, int seatsNumber) {
        try {
            Flight flight = getFlight(id);
            int availableSeats = getAvailableSeats(id);

            flight.setAvailableSeats(availableSeats + seatsNumber);
            return true;
        } catch (Exception e) {
            throw new FlightServiceException(FlightServiceException.ERROR_INCREASING_AVAILABLE_SEATS, e);
        }

    }

    public void saveData() {
        try {
            flightDao.saveDataBase();
        } catch (Exception e) {
            throw new FlightServiceException(FlightServiceException.ERROR_SAVING_DATA, e);
        }

    }

    public void loadData() {
        try {
            flightDao.loadDataBase();
        } catch (IOException | ClassNotFoundException e) {
            throw new FlightServiceException(FlightServiceException.ERROR_LOADING_DATA, e);
        }
    }

}
