
import controller.FlightController;
import controller.OrderController;

import java.io.IOException;

public class Application {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        FlightController flightController = new FlightController();
        flightController.loadData();
        flightController.displayAllFlights();

        OrderController orderController = new OrderController();
        orderController.loadData();
        orderController.printBase();
    }
}