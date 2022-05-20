package com.monolito.personas.exception;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {
  @ExceptionHandler(PersonNotFoundException.class)
  public ResponseEntity<Object> handlePersonNotFoundException(
      PersonNotFoundException ex, WebRequest request) {

    Map<String, Object> response = new LinkedHashMap<>();
    response.put("timestamp", LocalDateTime.now());
    response.put("message", ex.getMessage());
    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(FieldErrorException.class)
  public ResponseEntity<Object> handleFieldErrorException(
      FieldErrorException ex, WebRequest request) {

    Map<String, Object> response = new LinkedHashMap<>();
    List<String> errors = Arrays.asList(ex.getMessage().split("\\s*,\\s*"));
    response.put("timestamp", LocalDateTime.now());
    response.put("message", "Error al realizar la operacion por favor verifique los campos");
    response.put("errors", errors);
    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(ImageNotFoundException.class)
  public ResponseEntity<Object> handleImageNotFoundException(
      ImageNotFoundException ex, WebRequest request) {

    Map<String, Object> response = new LinkedHashMap<>();
    response.put("timestamp", LocalDateTime.now());
    response.put("message", ex.getMessage());
    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(ReadImageFileException.class)
  public ResponseEntity<Object> handleReadImageFileException(
      ReadImageFileException ex, WebRequest request) {

    Map<String, Object> response = new LinkedHashMap<>();
    response.put("timestamp", LocalDateTime.now());
    response.put("message", ex.getMessage());
    response.put("error", ex.getCause().getMessage());
    ex.getCause().printStackTrace();
    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(ImageRequiredException.class)
  public ResponseEntity<Object> handleImageRequiredException(
      ImageRequiredException ex, WebRequest request) {

    Map<String, Object> response = new LinkedHashMap<>();
    response.put("timestamp", LocalDateTime.now());
    response.put("message", ex.getMessage());
    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

}
