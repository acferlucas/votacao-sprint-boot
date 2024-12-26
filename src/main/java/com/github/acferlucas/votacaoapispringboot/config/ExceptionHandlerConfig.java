package com.github.acferlucas.votacaoapispringboot.config;

import com.github.acferlucas.votacaoapispringboot.exception.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandlerConfig {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleRuntimeException(RuntimeException ex, WebRequest request) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenericException(Exception ex, WebRequest request) {
        return buildErrorResponse("Erro interno do servidor. Tente novamente mais tarde.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<Object> buildErrorResponse(String message, HttpStatus status) {
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("timestamp", LocalDateTime.now());
        errorDetails.put("status", status.value());
        errorDetails.put("error", status.getReasonPhrase());
        errorDetails.put("message", message);

        return new ResponseEntity<>(errorDetails, status);
    }

}
