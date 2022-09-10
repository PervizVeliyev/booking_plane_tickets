package console;

import controller.FlightController;
import controller.UserController;
import entity.User;
import logging.Logger;

public class CancelBooking {
    public static void run(Console console, User loggedIn){
        try {
            console.print("Please, write the booking id:");
            int id = Integer.parseInt(console.nextLine());
            UserController userController = new UserController();
            FlightController flightController = new FlightController();
            flightController.increaseCapacity(id);
            console.printLine(userController.cancelBookingFromUser(id, loggedIn.getId()));
        } catch (NumberFormatException e) {
            console.printLine("Please, provide a number for id.");
            Logger.error(e.getMessage());
        }
    }
}
