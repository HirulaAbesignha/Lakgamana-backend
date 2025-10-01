package com.example.train.exception;
import org.springframework.http.*; import org.springframework.web.bind.MethodArgumentNotValidException; import org.springframework.web.bind.annotation.*; import java.util.*;
@ControllerAdvice public class RestExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class) public ResponseEntity<?> handleNotFound(ResourceNotFoundException ex){ Map<String,String> m=new HashMap<>(); m.put("error",ex.getMessage()); return new ResponseEntity<>(m,HttpStatus.NOT_FOUND);}    
    @ExceptionHandler(MethodArgumentNotValidException.class) public ResponseEntity<?> handleValidation(MethodArgumentNotValidException ex){ Map<String,String> errors=new HashMap<>(); ex.getBindingResult().getFieldErrors().forEach(fe -> errors.put(fe.getField(),fe.getDefaultMessage())); return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);}    
    @ExceptionHandler(Exception.class) public ResponseEntity<?> handleAll(Exception ex){ Map<String,String> m=new HashMap<>(); m.put("error",ex.getMessage()); return new ResponseEntity<>(m,HttpStatus.INTERNAL_SERVER_ERROR);}    
}