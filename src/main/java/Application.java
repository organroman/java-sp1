
import controller.FlightController;

import java.io.IOException;

public class Application {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        FlightController flightController = new FlightController();

        flightController.loadData();
        flightController.displayAllFlights();

    }
}
