package console;

import controller.UserController;
import entity.User;

public class MyBookings {
    public static void run(Console console, User loggedIn){
        UserController userController = new UserController();
        userController.getUser(loggedIn.getId()).get().getBookings()
                .forEach(booking -> console.printLine(booking.toString()));
    }
}
