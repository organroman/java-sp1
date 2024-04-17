package entity;

import exception.Exception;

import java.io.IOException;
import java.util.Scanner;

public class MainMenu {
    private static final Scanner scanner = new Scanner(System.in);
    private static boolean exitForApp = true;
    // ControllerObject
    //ControllerObject2

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
    private static void Exit(){exitForApp = false; } //+saveFile
    public static int getAndValidateNumber() throws IOException{
        int number;
        while (true) {
            try {
                number = scanner.nextInt();
                if (number >= 1 && number <= 6){
                    throw new Exception.NumberOutOfScopeException();
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
    static void runApp()  {
        boolean exit = true;
        //loadFile
        System.out.println(showOurMenu());
        try {
            do {
                System.out.print("Enter the number of command from the list: ");
                handleMenu(getAndValidateNumber());
                System.out.println(showOurMenu());
            }while (exitForApp);
        }
        catch (IOException e){
            System.out.println("It's not a valid number. Please enter an integer.");
        }
   }
}
