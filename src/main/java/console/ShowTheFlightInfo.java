package console;

import controller.FlightController;
import entity.Flight;

import java.util.Optional;

public class ShowTheFlightInfo {
    public static void run(Console console){
        FlightController flightController = new FlightController();

        console.print("Please, enter Airline code (e.g AA325):");
        String choice = console.nextLine();

        Optional<Flight> flight = flightController.get(choice);

        flight.ifPresentOrElse(flight1 -> console.printLine(String.format("Date: %s\nTime: %s \nDestination: %s (%s)\nFlight No: %s%d\nAmount of free seats: %d",
                flight1.getTime().toLocalDate(), flight1.getTime().toLocalTime(), flight1.getAirportTo().toString(),
                flight1.getAirportTo().getCode(), flight1.getAirline().getDesignator(), flight1.getCode(), flight1.getCapacity())),
                () -> console.printLine("No such flight found."));
    }
}
