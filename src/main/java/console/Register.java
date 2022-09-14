package console;

import controller.UserController;
import entity.User;
import exception.UsernameExists;
import utility.Validator;

public class Register {
    public static void run(Console console) {
        UserController userController = new UserController();
        String regex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,20}$";

        try {
            String username;
            do {
                console.print("Please, write username (at least 5 character):");
                username = console.nextLine();
                userController.userChecker(username);
            } while (username.length() <= 4);

            String password;
            do {
                console.print("Please, write password (at least 1 number, one lowercase, one uppercase and length between 8-20):");
                password = console.nextLine();
            } while (!Validator.isValidPassword(password, regex));

            userController.saveUser(new User(username, password));
        } catch (UsernameExists e) {
            console.printLine("Such a username exists.");
            Register.run(console);
        }
    }
}
