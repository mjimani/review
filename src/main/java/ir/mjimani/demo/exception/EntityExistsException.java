package ir.mjimani.demo.exception;

public class EntityExistsException extends RuntimeException {
    String message;

    public EntityExistsException(String message) {
        super(message);
        this.message = message;
    }
}
