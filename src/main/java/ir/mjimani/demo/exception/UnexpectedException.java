package ir.mjimani.demo.exception;

public class UnexpectedException extends RuntimeException {
    String message;

    public UnexpectedException(Exception e) {
        this.message = e.getMessage();
    }

    public UnexpectedException(String message) {

        super(message);
        this.message = message;
    }
}
