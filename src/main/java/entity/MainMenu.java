package entity;

import controller.FlightController;
import controller.OrderController;
import exception.Exception;
import exception.FlightServiceException;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MainMenu {
    private static final Scanner scanner = new Scanner(System.in);
    private static boolean exitForApp = true;

    static FlightController flightController = new FlightController();
    static OrderController orderController = new OrderController();

    static String showOurMenu() {
        StringBuilder menuCommands = new StringBuilder();
        menuCommands.append("\n1. Show flights in the next 24 hours\n")
                .append("2. Show flight information\n")
                .append("3. Search for airline tickets\n")
                .append("4. Cancel my reservation by number\n")
                .append("5. My flights\n")
                .append("6. Exit\n");
        return menuCommands.toString();
    }

    static void handleMenu(int menuNumber) throws IOException {
        switch (menuNumber) {
            case 1 -> showFlights();
            case 2 -> flightInfo();
            case 3 -> availableTicket();
            case 4 -> cancelReservation();
            case 5 -> myflights();
            case 6 -> Exit();
        }
    }

    public static void showFlights() throws IOException {
        System.out.println("| Flight ID | Departure  | Destination          | Available seats | Data  | Time  |");
        flightController.getTodayFLights().forEach(x -> {
            System.out.println(toFormattedString(x));
        });
    }

    private static void flightInfo()  {
        try {
            System.out.println("Enter the flight number you are interested in");
            Flight temp = flightController.getFlight(getAndValidateNumberForFlightInfo());
            System.out.println(toFormattedString(temp));

        }catch (IOException e) {
        }
        catch (NullPointerException e) {
        }
    }

    private static void availableTicket() throws IOException {
        System.out.println("The destination?");
        String destination = scanner.nextLine();
        System.out.println("The date for which you are looking for tickets");
        System.out.println("In the format of 22/04/2024.");
        String data = scanner.nextLine();
        System.out.println("How many tickets do you need? ");
        int needTicket = validateNumber();
        try {
            if(!flightController.findFlights(destination, data, needTicket).isEmpty()) {
                System.out.println("Found options for your request");
                flightController.findFlights(destination, data, needTicket)
                        .forEach(x -> System.out.println(toFormattedString(x)));
            }else {
                System.out.println("There are no flights behind such a request");
            }
        }catch (FlightServiceException e){System.out.println("There are no flights behind such a request");}

        System.out.println("\nEnter 0 to go to the main menu or flight number to book tickets");
        int id = validateNumber();
        if (id != 0) {
            System.out.println("Keep the first and last names of all passengers who book tickets.");
            List<Person> personInOrder = new ArrayList<>();
            Person buyer = createPerson("buyer");
            for (int i = 0; i < needTicket - 1; i++) {
                System.out.printf("Enter name %d passenger\n", ++i);
                String nameOfPassenger = scanner.nextLine();
                System.out.printf("Enter surname %d passenger\n", ++i);
                String surnameOfPassenger = scanner.nextLine();
                personInOrder.add(new Person(nameOfPassenger, surnameOfPassenger));
            }
            orderController.create(flightController.getFlight(id), buyer, needTicket, personInOrder);
            flightController.decreaseAvailableSeats(id, needTicket);
        }
    }


    private static void cancelReservation() {
        System.out.println("Enter the number of the order you want to cancel");
        orderController.deleteById(validateNumber());
    }

    private static void myflights() {
        List<Order> orders = orderController.getMyOrders(createPerson("your"));
        try {

            if (orders.isEmpty()){
                throw new Exception.NonExistentOrderException();
            }
            else {
                orders.forEach(MainMenu::orderToPrint);
            }
        }catch (Exception.NonExistentOrderException e){
            System.out.println("You don`t have orders");
        }
    }

    private static void Exit() throws IOException {
        flightController.saveData();
        orderController.saveDataBase();
        exitForApp = false;
    }

    public static Person createPerson(String str) {
        System.out.printf("Enter %s name\n", str);
        String name = scanner.nextLine();
        System.out.printf("Enter %s surname\n", str);
        String surname = scanner.nextLine();
        return new Person(name, surname);
    }

    public static int validateNumber() {
        int number;
        while (true) {
            try {
                number = scanner.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Please just keep a number.");
                scanner.nextLine();
            }
        }
        scanner.nextLine();
        return number;
    }

    public static int getAndValidateNumberForMenu() {
        int number;
        while (true) {
            try {
                number = scanner.nextInt();
                if (number < 1 || number > 6) {
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

    public static int getAndValidateNumberForFlightInfo() throws IOException {
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
    public static void orderToPrint (Order order){
        System.out.println(toFormattedString(order.getFlight()));
        System.out.printf("\nOrder number: %d\n",order.getOrderId());
        System.out.printf("Passenger : %s \n",order.getPassengers().toString());
    }
    public static String toFormattedString(Flight flight) {
        StringBuilder data = new StringBuilder(flightController.getDateOfDeparture(flight.getId()));
        data.delete(5,10);
        String time = flightController.getTimeOfDeparture(flight.getId());
        return String.format("| %-9s | %-10s | %-20s | %-15d | %-5s | %-5s |",
                flight.getId(), flight.getDeparture(), flight.getDestination(), flight.availableSeats,
                data , time);
    }
    public static void runApp() {
        try {
            flightController.loadData();
            orderController.loadData();
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
