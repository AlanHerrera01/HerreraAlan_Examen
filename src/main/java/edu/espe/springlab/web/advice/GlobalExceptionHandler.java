package edu.espe.springlab.web.advice;

import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * MANEJO GLOBAL DE EXCEPCIONES
 * 
 * Captura todas las excepciones de la aplicación y devuelve
 * respuestas JSON estandarizadas al cliente.
 * 
 * @ControllerAdvice - Aplica este manejador a todos los controllers
 * 
 * EXCEPCIONES MANEJADAS:
 * - NotFoundException (404) → Recurso no encontrado
 * - ConflictException (409) → Email duplicado
 * - MethodArgumentNotValidException (400) → Validaciones fallidas
 * - Exception (500) → Errores genéricos
 * 
 * FORMATO DE RESPUESTA:
 * {
 *   "timestamp": "2025-11-14T10:30:00",
 *   "status": 404,
 *   "error": "Not Found",
 *   "message": "Estudiante no encontrado"
 * }
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    // 404 del negocio
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFound(NotFoundException ex){
        return error(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    //409 cuando existe correos duplicados
    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<?> handleConflict(ConflictException ex){
        return error(HttpStatus.CONFLICT, ex.getMessage());
    }

    //400 por validaciones
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidation(MethodArgumentNotValidException ex){
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.BAD_REQUEST.value());
        Map<String, String> errors = new HashMap<>();
        for(FieldError field : ex.getBindingResult().getFieldErrors()){
            errors.put(field.getField(), field.getDefaultMessage());
        }
        body.put("errors", errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    //500 generico
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGeneric(Exception ex){
        return error(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
    }

    //Construye el JSON estandar de error
    private ResponseEntity<Map<String, Object>> error(HttpStatus status, String message) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("message", message);
        return ResponseEntity.status(status).body(body);
    }
}
