package ir.mjimani.demo.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class InvalidInputException extends RuntimeException {

    private String message;

    public InvalidInputException(String message) {
        this.message = message;
    }

}