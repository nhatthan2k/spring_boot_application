package com.appspringboot.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({MethodArgumentNotValidException.class, ConstraintViolationException.class, ResourceNotFoundException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidationException(Exception e, WebRequest request) {
        ErrorResponse error = new ErrorResponse();
        error.setTimestamp(new Date());
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setPath(request.getDescription(false).replace("uri=", ""));
        String mess = e.getMessage();
        if (e instanceof MethodArgumentNotValidException) {
            int start = mess.lastIndexOf('[');
            int end = mess.lastIndexOf(']');
            mess = mess.substring(start + 1, end - 1);
            error.setError("Payload invalid");
        } else if (e instanceof ConstraintViolationException) {
            mess = mess.substring(mess.indexOf(" ") + 1);
            error.setError("PathVariable invalid");
        }
        error.setMessage(mess);
        return error;
    }
}
