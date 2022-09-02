package ir.mjimani.demo.exception;

public class UnavailableProcessException extends RuntimeException {
    String message;

    public UnavailableProcessException(String message) {
        super(message);
        this.message = message;
    }
}
