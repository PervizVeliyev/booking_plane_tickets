package console;

import controller.FlightController;

public class OnlineBoard {
    public static void run(Console console){
        FlightController flightController = new FlightController();

        console.printLine("AIRLINE CODE |    FROM     --->       TO      |       TIME       | DURATION |  CAPACITY ");

        flightController.getAllFlights()
                .forEach(flight -> console.printLine(flight.toString()));
    }
}
