package exception;

public class CollectionDaoException extends RuntimeException {
    public static final String ERROR_RETRIEVING_ALL_FLIGHTS = "Error retrieving all flights";
    public static final String ERROR_CREATING_NEW_FLIGHT = "Error creating new flight";
    public static final String ERROR_GETTING_FLIGHT = "Error getting the flight";
    public static final String ERROR_UPDATING_FLIGHT = "Error updating flight";
    public static final String ERROR_DELETING_FLIGHT = "Error deleting flight";
    public static final String ERROR_LOADING_DATA = "Error in loading data";
    public static final String ERROR_SAVING_DATA = "Error in saving data";

    public static final String ERROR_GET_ALL_ORDERS = "Error retrieving all orders";
    public static final String ERROR_CREATING_NEW_ORDER = "Error creating new order";
    public static final String ERROR_GETTING_ORDER = "Error getting the order";
    public static final String ERROR_UPDATING_ORDER = "Error updating order";
    public static final String ERROR_DELETE_ORDER = "Error delete order";
    public static final String ERROR_LOADING_DATA_ORDERS = "Error in loading orders data";
    public static final String ERROR_SAVING_DATA_ORDERS = "Error in saving orders data";

    public CollectionDaoException(String message) {
        super(message);
    }

    public CollectionDaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
