package com.endava.finalproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleItemNotFoundException(ItemNotFoundException exception) {
        return errorResponse(exception, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SupermarketNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleSupermarketNotFoundException(SupermarketNotFoundException exception) {
        return errorResponse(exception, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleSupermarketAlreadyExistException(SupermarketAlreadyExistException exception) {
        return errorResponse(exception, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleNotEnoughCashException(NotEnoughCashException exception) {
        return errorResponse(exception, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<ErrorResponse> errorResponse(RuntimeException runtimeException, HttpStatus httpStatus) {

        ErrorResponse errors = new ErrorResponse();
        errors.setTimeStamp(LocalDateTime.now());
        errors.setMessage(runtimeException.getMessage());
        errors.setStatus(httpStatus.value());

        return new ResponseEntity<>(errors, httpStatus);
    }
}
