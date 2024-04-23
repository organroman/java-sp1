package exception;

public class CollectionOrderDaoException extends RuntimeException {

    public static final String ERROR_GET_ALL_ORDERS = "Error retrieving all orders";
    public static final String ERROR_CREATING_NEW_ORDER = "Error creating new order";
    public static final String ERROR_GETTING_ORDER = "Error getting the order";
    public static final String ERROR_UPDATING_ORDER = "Error updating order";
    public static final String ERROR_DELETE_ORDER = "Error delete order";
    public static final String ERROR_LOADING_DATA_ORDERS = "Error in loading orders data";
    public static final String ERROR_SAVING_DATA_ORDERS = "Error in saving orders data";

    public CollectionOrderDaoException(String message) {
        super(message);
    }

    public CollectionOrderDaoException(String message, Throwable cause) {
        super(message, cause);
    }

}
