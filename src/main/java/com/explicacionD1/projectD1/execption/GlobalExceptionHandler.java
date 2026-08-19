package com.explicacionD1.projectD1.execption;

import io.micrometer.observation.annotation.ObservationKeyValue;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handlerNotFound(EntityNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(
                        LocalDateTime.now(),
                        HttpStatus.NOT_FOUND.value(),
                        ex.getMessage(),
                        "RESOURCE NOT FOUND"

                ) );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handlerValidationError(MethodArgumentNotValidException ex){
        Map<String, Object> response=new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("errorCode", "VALIDATION_FAILED");

        Map<String, String> errors = new HashMap<>();
        for(FieldError error : ex.getBindingResult().getFieldErrors()){
            errors.put(error.getField(), error.getDefaultMessage());
        }
        response.put("errors", errors);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleGenericErrors(Exception e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ErrorResponse(LocalDateTime.now(), HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error interno del servidor",
                        "INTERNALL SERVER ERROR")
        );
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorResponse> handlerNotFound(NoHandlerFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ErrorResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), "Ruta no encontrada o inclompleta",
                        "ERROR NO HANDLER FOUND")
        );
    }
}
