package service;

import collection.CollectionFlightDao;
import controller.FlightController;
import dao.Dao;
import entity.Flight;
import entity.MainMenu;
import entity.Order;
import entity.Person;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import static entity.MainMenu.toFormattedString;
import static org.junit.Assert.assertEquals;

public class MenuTest {

    @Test
    public void prettyFormatTest  (){
        LocalDateTime dateTime1 = LocalDateTime.of(2004, 4, 18, 21, 0);
        long timestamp1 = dateTime1.toInstant(ZoneOffset.UTC).toEpochMilli();
        LocalDateTime dateTime2 = LocalDateTime.of(2004, 4, 18, 22, 0);
        long timestamp2 = dateTime2.toInstant(ZoneOffset.UTC).toEpochMilli();
        LocalDateTime dateTime3 = LocalDateTime.of(2004, 4, 18, 23, 0);
        long timestamp3 = dateTime3.toInstant(ZoneOffset.UTC).toEpochMilli();

        Flight flight1 = new Flight( "Lublin", 16, timestamp1);
        Flight flight2 = new Flight( "Riga", 25, timestamp2);
        Flight flight3 = new Flight( "Astana", 21, timestamp3);

        MainMenu mainMenu = new MainMenu();

        try {
            mainMenu.showFlights();
            System.out.println(toFormattedString(flight1));
            System.out.println(toFormattedString(flight2));
            System.out.println(toFormattedString(flight3));
        }
        catch (IOException e){
            System.out.println("No flight");
        }
    }
    @Test
    public void OrderFormatTest  (){
        LocalDateTime dateTime1 = LocalDateTime.of(2004, 4, 18, 21, 0);
        long timestamp1 = dateTime1.toInstant(ZoneOffset.UTC).toEpochMilli();
        Flight flight1 = new Flight( "Lublin", 16, timestamp1);
        Person person1 = new Person("egor","antorm");
        Person person2 = new Person("er","antorm4");
        Person person3 = new Person("ego","antor2m");
        List<Person> people = new ArrayList<>();
        people.add(person1);
        people.add(person3);
        Order order = new Order(flight1,person2,2 ,people);
        orderToPrint(order);
    }
    public static void orderToPrint (Order order){
        System.out.println(toFormattedString(order.getFlight()));
        System.out.printf("Order number: %d\n",order.getOrderId());
        System.out.printf("Passenger : %s \n",order.getPassengers().toString());
    }
}
