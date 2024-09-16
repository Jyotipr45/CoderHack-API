package com.crio.coderhack.handler;

import com.crio.coderhack.dto.ErrorBodyDto;
import com.crio.coderhack.exception.UserNotFoundException;
import com.crio.coderhack.exception.InvalidScoreException;
import com.crio.coderhack.exception.InvalidBadgeSetException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorBodyDto> handleUserNotFound(UserNotFoundException ex) {
        ErrorBodyDto error = new ErrorBodyDto("USER_NOT_FOUND", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidScoreException.class)
    public ResponseEntity<ErrorBodyDto> handleInvalidScore(InvalidScoreException ex) {
        ErrorBodyDto error = new ErrorBodyDto("INVALID_SCORE", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidBadgeSetException.class)
    public ResponseEntity<ErrorBodyDto> handleInvalidBadgeSet(InvalidBadgeSetException ex) {
        ErrorBodyDto error = new ErrorBodyDto("INVALID_BADGE_SET", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorBodyDto> handleGeneralException(Exception ex) {
        ErrorBodyDto error = new ErrorBodyDto("INTERNAL_SERVER_ERROR", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
