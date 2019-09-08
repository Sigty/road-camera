package com.dulik.jnetworks.roadcamera.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import javax.validation.ConstraintViolationException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoUniqException.class)
    public ResponseEntity<AwesomeException> NoUniqExceptionHandler(NoUniqException ex) {
        return new ResponseEntity<>(new AwesomeException(HttpStatus.BAD_REQUEST,
                "Not unique carNumber : " + ex.getMessage(), LocalDateTime.now()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<AwesomeException> ConstraintViolationExceptionHandler(ConstraintViolationException ex) {
        return new ResponseEntity<>(new AwesomeException(HttpStatus.BAD_REQUEST,
                "Not valid : " + ex.getLocalizedMessage(), LocalDateTime.now()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<AwesomeException> NullPointerHandler() {
        return new ResponseEntity<>(new AwesomeException(HttpStatus.BAD_REQUEST,
                "Something wrong...", LocalDateTime.now()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<AwesomeException> IllegalArgumentExceptionHandler(IllegalArgumentException ex) {
        return new ResponseEntity<>(new AwesomeException(HttpStatus.INTERNAL_SERVER_ERROR,
                ex.getLocalizedMessage(), LocalDateTime.now()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<AwesomeException> EntityNotFoundExceptionHandler(EntityNotFoundException ex) {
        return new ResponseEntity<>(new AwesomeException(HttpStatus.BAD_REQUEST,
                ex.getLocalizedMessage(), LocalDateTime.now()), HttpStatus.BAD_REQUEST);
    }

    @Data
    @AllArgsConstructor
    private static class AwesomeException {

        private HttpStatus status;
        private String message;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
        private LocalDateTime timestamp;
    }
}