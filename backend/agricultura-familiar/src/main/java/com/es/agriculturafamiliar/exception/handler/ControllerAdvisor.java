package com.es.agriculturafamiliar.exception.handler;

import java.time.LocalDateTime;
import java.util.List;

import com.es.agriculturafamiliar.dto.ExceptionPayloadDTO;
import com.es.agriculturafamiliar.exception.ResourceNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ControllerAdvisor {
    @ExceptionHandler(value = {ResourceNotFoundException.class})
	protected ResponseEntity<Object> handleUserAlreadyExistsException(ResourceNotFoundException exception) {
		ExceptionPayloadDTO exceptionPayload = ExceptionPayloadDTO.builder()
				.timestamp(LocalDateTime.now())
				.title("Resource not found")
				.statusCode(HttpStatus.NOT_FOUND.value())
				.description(exception.getMessage()) 
				.build();
		
		return new ResponseEntity<>(exceptionPayload, HttpStatus.NOT_FOUND);
	}
    
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        String errorMessage = fieldErrors.get(0).getDefaultMessage();
        ExceptionPayloadDTO exceptionPayload = ExceptionPayloadDTO.builder()
                .timestamp(LocalDateTime.now())
                .title("Validation Error")
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .description(errorMessage)
                .build();

        return ResponseEntity.badRequest().body(exceptionPayload);
    }
}
