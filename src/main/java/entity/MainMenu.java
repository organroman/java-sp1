package entity;

import exception.Exception;

import java.io.IOException;
import java.util.Scanner;

public class MainMenu {
    private static final Scanner scanner = new Scanner(System.in);
    // Контролер 1
    //Контролер 2

    static String showOurMenu (){
        StringBuilder menuCommands = new StringBuilder();
        menuCommands.append("1. Show flights in the next 24 hours\n")
                    .append("2. Show flight information\n") // or "Show flight information by number"
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
    private static void showFlights() {

    }
    private static void flightInfo() {}
    private static void availableTicket(){}
    private static void cancelReservation(){}
    private static void myflights(){}
    private static boolean Exit(){ return false;}
    public static int getAndValidateNumber(String message) {
        int number;
        while (true) {
            System.out.print(message);
            try {
                number = scanner.nextInt();
                if (number >= 1 && number <= 6){
                    throw new  Exception.NumberOutOfScopeException();
                }
                break;
            } catch (Exception.NumberOutOfScopeException e){
                System.out.println("Your number does not match the menu choice");
                scanner.nextLine();
            } catch (java.util.InputMismatchException e) {
                System.out.println("It's not a valid number. Please enter an integer.");
                scanner.nextLine();
            }
        }
        scanner.nextLine();
        return number;
    }
}
