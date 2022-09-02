package ir.mjimani.demo.exception;

import ir.mjimani.demo.model.Response;

import java.util.Collections;
import java.util.List;

public class RestExceptionResponse extends Response<RestExceptionResponse> {

    public RestExceptionResponse(List<String> messages) {
        super(null);
        success = false;
        this.messages = messages;
    }

    public RestExceptionResponse(String message) {
        super(null);
        success = false;
        this.messages = Collections.singletonList(message);
    }
}