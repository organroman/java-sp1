package service;

import collection.CollectionFlightDao;
import controller.FlightController;
import dao.Dao;
import entity.Flight;
import entity.MainMenu;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;

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

        Flight flight1 = new Flight("Kyiv", "Lublin", 16, timestamp1);
        Flight flight2 = new Flight("Kyiv", "Riga", 25, timestamp2);
        Flight flight3 = new Flight("Kyiv", "Astana", 21, timestamp3);

        MainMenu mainMenu = new MainMenu();

        try {
            mainMenu.showFlights();
            System.out.println(mainMenu.toFormattedString(flight1));
            System.out.println(mainMenu.toFormattedString(flight2));
            System.out.println(mainMenu.toFormattedString(flight3));
        }
        catch (IOException e){
            System.out.println("No flight");
        }


    }
}
