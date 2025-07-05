package com.cosmetics.products.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import feign.FeignException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NumberFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, String>> handleInvalidBrandIdFormat(NumberFormatException e) {
        Map<String, String> response = new HashMap<>();
        response.put("error", "Format d'ID invalide");
        response.put("message", e.getMessage());
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Map<String, String>> handleBrandNotFound(IllegalArgumentException e) {
        Map<String, String> response = new HashMap<>();
        response.put("error", "Ressource non trouvée");
        response.put("message", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(FeignException.NotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Map<String, String>> handleFeignNotFound(FeignException.NotFound e) {
        Map<String, String> response = new HashMap<>();
        response.put("error", "Service externe - Ressource non trouvée");
        response.put("message", "La ressource demandée n'existe pas dans le service externe");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(FeignException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public ResponseEntity<Map<String, String>> handleFeignException(FeignException e) {
        Map<String, String> response = new HashMap<>();
        response.put("error", "Erreur de communication avec le service externe");
        response.put("message", "Service temporairement indisponible");
        response.put("details", e.getMessage());
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Map<String, String>> handleRuntimeException(RuntimeException e) {
        Map<String, String> response = new HashMap<>();
        response.put("error", "Erreur interne du serveur");
        response.put("message", e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}