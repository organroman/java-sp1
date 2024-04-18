package entity;

import collection.CollectionFlightDao;
import collection.CollectionOrderDao;
import controller.FlightController;
import dao.Dao;
import exception.Exception;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MainMenu {
    private static final Scanner scanner = new Scanner(System.in);
    private static boolean exitForApp = true;

    FlightController flightController = new FlightController();
    Dao<Order> orderDao = new CollectionOrderDao(); // Change to OrderController

    static String showOurMenu() {
        StringBuilder menuCommands = new StringBuilder();
        menuCommands.append("1. Show flights in the next 24 hours\n")
                .append("2. Show flight information\n") // or "Show flight information by number" ?
                .append("3. Search for airline tickets\n")
                .append("4. Cancel my reservation by number\n")
                .append("5. My flights\n")
                .append("6. Exit\n");
        return menuCommands.toString();
    }

    void handleMenu(int menuNumber) throws IOException {
        switch (menuNumber) {
            case 1 -> showFlights();
            case 2 -> flightInfo();
            case 3 -> availableTicket();
            case 4 -> cancelReservation();
            case 5 -> myflights();
            case 6 -> Exit();
        }
    }

    public void showFlights() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        System.out.println("| Flight ID | Departure  | Destination          | Available seats | Time  |");
        flightController.getTodayFLights().forEach(x -> {
                System.out.println(toFormattedString(x));
            });
        System.out.println("\n");
        System.out.println(showOurMenu());
    }

    private void flightInfo() throws IOException {
        System.out.println("Enter the flight number you are interested in");
        Flight temp = flightController.getFlight(getAndValidateNumberForFlightInfo());
        System.out.println(toFormattedString(temp));
        System.out.println("\n");
        System.out.println(showOurMenu());
    }

    private static void availableTicket() {
    }

    private static void cancelReservation() {
    }

    private static void myflights() {
    }

    private void Exit() throws IOException {
        flightController.saveData();
        orderDao.saveDataBase(); // Change to OrderController
        exitForApp = false;
    }

    public static int getAndValidateNumberForMenu()  {
        int number;
        while (true) {
            try {
                number = scanner.nextInt();
                if (number >= 1 && number <= 6) {
                    throw new Exception.NumberOutOfScopeException();
                }
                break;
            } catch (Exception.NumberOutOfScopeException e) {
                System.out.println("Your number does not match the menu choice");
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("It's not a valid number. Please enter an integer.");
                scanner.nextLine();
            }
        }
        scanner.nextLine();
        return number;
    }

    public int getAndValidateNumberForFlightInfo() throws IOException {
        int number = 0;
        try {
            number = scanner.nextInt();
            if (flightController.getFlight(number) == null) {
                throw new Exception.NonExistentFlightException();
            }

        } catch (Exception.NonExistentFlightException e) {
            System.out.println("There is no flight with that number");
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("It's not a valid number. Please enter an integer.");
            scanner.nextLine();
        }

        return number;
    }
    public String toFormattedString(Flight flight)  {
        return String.format("| %-9s | %-10s | %-20s | %-15d | %-5s |",
                flight.getId(), flight.getDeparture(), flight.getDestination(), flight.availableSeats, flightController.getTimeOfDeparture(flight.getId()));
    }

    void runApp() {
        try {
            boolean exit = true;
            flightController.loadData();
            orderDao.loadDataBase(); // Change to OrderController
            System.out.println(showOurMenu());
            do {
                System.out.print("Enter the number of command from the list: ");
                handleMenu(getAndValidateNumberForMenu());
                System.out.println(showOurMenu());
            } while (exitForApp);
        } catch (IOException e) {
            System.out.println("Something went wrong. Try it again.");
        } catch (ClassNotFoundException e) {
            System.out.println();
        }
    }
}
