package exception;

public class FlightServiceException extends RuntimeException{
    public static final String ERROR_RETRIEVING_ALL_FLIGHTS = "Error retrieving all flights";
    public static final String ERROR_DISPLAYING_ALL_FLIGHTS = "Error displaying all flights";
    public static final String ERROR_CREATING_NEW_FLIGHT = "Error creating new flight";
    public static final String ERROR_GETTING_FLIGHT = "Error getting the flight";
    public static final String ERROR_UPDATING_FLIGHT = "Error updating flight";
    public static final String ERROR_DELETING_FLIGHT = "Error deleting flight";
    public static final String ERROR_GETTING_TIME_FLIGHT = "Error getting time of flight";
    public static final String ERROR_GETTING_DATE_FLIGHT = "Error getting date of flight";
    public static final String ERROR_GETTING_DESTINATION_FLIGHT = "Error getting destination of flight";
    public static final String ERROR_GETTING_DEPARTURE_FLIGHT = "Error getting departure of flight";
    public static final String ERROR_GETTING_AVAILABLE_SEATS_FLIGHT = "Error getting available seats of flight";
    public static final String ERROR_FINDING_FLIGHTS = "Error finding flights";
    public static final String ERROR_COUNTING_FLIGHTS = "Error counting flights";
    public static final String ERROR_GETTING_TODAY_FLIGHTS = "Error in getting flights for next 24 hours";
    public static final String ERROR_DECREASING_AVAILABLE_SEATS = "Error in decreasing available seats";
    public static final String ERROR_INCREASING_AVAILABLE_SEATS = "Error in increasing available seats";
    public static final String ERROR_SAVING_DATA = "Error in saving data";
    public static final String ERROR_LOADING_DATA = "Error in loading data";
    public static final String ERROR_FLIGHT_NOT_FOUND = "Error, flight not found";
    public FlightServiceException(String message) {
        super(message);
    }

    public FlightServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
