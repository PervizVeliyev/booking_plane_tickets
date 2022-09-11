package menu;

import console.*;
import controller.FlightController;
import exception.NonExistentMenuItem;
import logging.Logger;
import utility.FlightsGenerator;

public class LoginMenu {
    public static void run(Console console){
        FlightController flightController = new FlightController();

//      Checking if flights are empty
        if(flightController.getAllFlights().isEmpty())
            flightController.saveAllFlights(FlightsGenerator.generator(300));

        flightController.removeExpiredFlights();

        while(true) {
            try {
                console.printLine("-".repeat(30));
                console.printLine(" ".repeat(10) + "LOGIN MENU");
                console.printLine("-".repeat(30));
                console.printLine("""
                        1.Login
                        2.Register
                        3.Online Board
                        4.Show the flight info
                        5.Exit
                        """);
                console.print("Your choice:");
                String choice = console.nextLine();
                boolean breaker = false;
                switch (Integer.parseInt(choice)) {
                    case 1 -> Login.run(console);
                    case 2 -> Register.run(console);
                    case 3 -> OnlineBoard.run(console);
                    case 4 -> ShowTheFlightInfo.run(console);
                    case 5 -> breaker = true;
                    default -> throw new NonExistentMenuItem("Non-existent menu item", new RuntimeException());
                }
                if (breaker) break;
            } catch (NumberFormatException e) {
                console.printLine("Please, provide a number.");
                Logger.error(e.getMessage());
            } catch (NonExistentMenuItem e) {
                console.printLine("Please, choose option between 1-5.");
                Logger.error(e.getMessage());
            }
        }
    }
}
