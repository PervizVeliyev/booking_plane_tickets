package exception;

public class TicketsOverCapacity extends RuntimeException{
    public TicketsOverCapacity(String message, Throwable cause) {
        super(message, cause);
    }
}
