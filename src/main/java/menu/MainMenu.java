package menu;

import console.*;
import entity.User;
import exception.NonExistentMenuItem;
import logging.Logger;

public class MainMenu {
    public static void run(Console console, User loggedIn){
        while(true) {
            try {
                boolean breaker = false;
                console.printLine("-".repeat(50));
                console.printLine(" ".repeat(14) + "FLIGHT BOOKING MANAGER");
                console.printLine("-".repeat(50));
                console.printLine("""
                        1.Online Board
                        2.Show the flight info
                        3.Search and book a flight
                        4.My bookings
                        5.Cancel the booking
                        6.Logout
                        """);
                console.print("Your choice:");
                String choice = console.nextLine();
                switch (Integer.parseInt(choice)) {
                    case 1 -> OnlineBoard.run(console);
                    case 2 -> ShowTheFlightInfo.run(console);
                    case 3 -> SearchAndBookFlight.run(console, loggedIn);
                    case 4 -> MyBookings.run(console, loggedIn);
                    case 5 -> CancelBooking.run(console, loggedIn);
                    case 6 -> breaker = true;
                    default -> throw new NonExistentMenuItem("Non-existent menu item", new RuntimeException());
                }
                if (breaker) break;
            } catch (NumberFormatException e) {
                console.printLine("Please, provide a number.");
                Logger.error(e.getMessage());
            } catch (NonExistentMenuItem e) {
                console.printLine("Please, choose option between 1-6.");
                Logger.error(e.getMessage());
            }
        }
    }
}
