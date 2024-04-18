package util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class Helpers {

    public static boolean isSameDay(long dateTime, LocalDate dateToCompare) {
        LocalDate flightDate = Instant.ofEpochMilli(dateTime).atZone(ZoneId.systemDefault()).toLocalDate();
        return flightDate.equals(dateToCompare);
    }




}
