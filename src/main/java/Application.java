
import controller.FlightController;
import controller.OrderController;
import entity.Flight;

import java.io.IOException;
import java.util.List;

public class Application {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        FlightController flightController = new FlightController();
        flightController.loadData();

        OrderController orderController = new OrderController();
        orderController.loadData();

    }
}
