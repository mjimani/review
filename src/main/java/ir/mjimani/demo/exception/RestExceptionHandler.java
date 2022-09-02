package ir.mjimani.demo.exception;

import com.mongodb.MongoException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.*;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String error = "Malformed JSON request";
        return buildResponseEntity(new RestExceptionResponse(Collections.singletonList(error)), HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<Object> buildResponseEntity(RestExceptionResponse apiError, HttpStatus httpStatus) {
        return new ResponseEntity<>(apiError, httpStatus);
    }

    @ExceptionHandler(UnavailableProcessException.class)
    protected ResponseEntity<Object> handleUnavailableProcessException(Exception ex) {
        RestExceptionResponse apiError = new RestExceptionResponse(Collections.singletonList(ex.getMessage()));
        return buildResponseEntity(apiError, HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS);
    }

    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<Object> handleNotFoundException(Exception ex) {
        RestExceptionResponse apiError = new RestExceptionResponse(Collections.singletonList(ex.getMessage()));
        return buildResponseEntity(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidInputException.class)
    protected ResponseEntity<Object> handleInvalidInputException(InvalidInputException ex) {
        RestExceptionResponse apiError = new RestExceptionResponse(Collections.singletonList(ex.getMessage()));
        return buildResponseEntity(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnexpectedException.class)
    protected ResponseEntity<Object> handleCustomException(UnexpectedException ex) {
        RestExceptionResponse apiError = new RestExceptionResponse(ex.getMessage());
        return buildResponseEntity(apiError, HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS);
    }

    @ExceptionHandler(EntityExistsException.class)
    protected ResponseEntity<Object> handleEntityExistsException(EntityExistsException ex) {
        RestExceptionResponse apiError = new RestExceptionResponse(ex.getMessage());
        return buildResponseEntity(apiError, HttpStatus.UNPROCESSABLE_ENTITY);
    }


    @ExceptionHandler(MongoException.class)
    protected ResponseEntity<Object> handleMongoException(MongoException ex) {
        RestExceptionResponse apiError = new RestExceptionResponse("The server cannot process the request because it is malformed,it should not be retried.That's all we know.");
        return buildResponseEntity(apiError, HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS);
    }

}