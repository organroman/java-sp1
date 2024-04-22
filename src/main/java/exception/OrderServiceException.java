package exception;

public class OrderServiceException extends RuntimeException {
    public static final String ERROR_GET_MY_ORDERS = "Error retrieving my orders";
    public static final String ERROR_DISPLAYING_ALL_ORDER = "Error displaying all orders";
    public static final String ERROR_CREATING_NEW_ORDER = "Error creating new order";
    public static final String ERROR_GET_ORDER = "Error getting the order";
    public static final String ERROR_UPDATING_ORDER = "Error updating order";
    public static final String ERROR_DELETE_ORDER = "Error deleting order";
    public static final String ERROR_SAVING_DATA_ORDERS = "Error in saving data";
    public static final String ERROR_LOADING_DATA_ORDERS = "Error in loading data";

    public OrderServiceException(String message) {
        super(message);
    }

    public OrderServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
