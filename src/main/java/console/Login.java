package console;

import controller.UserController;
import entity.User;
import menu.MainMenu;

public class Login {
    public static void run(Console console){
        while(true) {
            UserController userController = new UserController();
            console.print("Please, write your login:");
            String login = console.nextLine();
            if(login.equalsIgnoreCase("exit")) break;
            console.print("Please, write your password:");
            String password = console.nextLine();
            if(password.equalsIgnoreCase("exit")) break;
            if (userController.getAllUsers().contains(new User(login, password))) {
                User loggedIn = userController.getAllUsers()
                                .stream()
                                .filter(user -> user.getLogin().equals(login.toLowerCase()) && user.getPassword().equals(password))
                                .findFirst()
                                .get();
                MainMenu.run(console, loggedIn);
                break;
            } else {
                console.printLine("Login or password is incorrect.\n" +
                        "If you don't have an account, please, register.\n" +
                        "Type \"exit\" to go back.");
            }
        }
    }
}
