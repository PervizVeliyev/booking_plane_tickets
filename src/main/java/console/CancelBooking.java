package console;

import controller.FlightController;
import controller.UserController;
import entity.User;
import logging.Logger;

import java.util.NoSuchElementException;

public class CancelBooking {
    public static void run(Console console, User loggedIn){
        UserController userController = new UserController();
        FlightController flightController = new FlightController();

        try {
            console.print("Please, write the booking id:");
            int id = Integer.parseInt(console.nextLine());

            flightController.increaseCapacity(id);

            console.printLine(userController.cancelBookingFromUser(id, loggedIn.getId()));
        } catch (NumberFormatException e) {
            console.printLine("Please, provide a number for id.");
            Logger.error(e.getMessage());
        } catch (NoSuchElementException e) {
            console.printLine("No such booking found.");
            Logger.error(e.getMessage());
        }
    }
}
