package exception;

public class UsernameExists extends RuntimeException{
    public UsernameExists(String message, Throwable cause) {
        super(message, cause);
    }
}
