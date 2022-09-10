package exception;

public class NonExistentMenuItem extends RuntimeException{
    public NonExistentMenuItem(String message, Throwable cause) {
        super(message, cause);
    }
}
