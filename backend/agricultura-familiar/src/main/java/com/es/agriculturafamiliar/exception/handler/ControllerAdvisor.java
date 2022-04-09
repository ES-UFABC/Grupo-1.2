package com.es.agriculturafamiliar.exception.handler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.es.agriculturafamiliar.dto.ExceptionPayloadDTO;
import com.es.agriculturafamiliar.exception.AuthException;
import com.es.agriculturafamiliar.exception.ResourceNotFoundException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;


@RestControllerAdvice
public class ControllerAdvisor {
    @ExceptionHandler(value = {ResourceNotFoundException.class})
	public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException exception) {
		ExceptionPayloadDTO exceptionPayload = ExceptionPayloadDTO.builder()
				.timestamp(LocalDateTime.now())
				.title("Recurso não encontrado")
				.statusCode(HttpStatus.NOT_FOUND.value())
				.description(new String[]{exception.getMessage()})
				.build();

		return new ResponseEntity<>(exceptionPayload, HttpStatus.NOT_FOUND);
	}


	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        List<String> errorMessages =  fieldErrors.stream().map(x -> x.getField() + ": " + x.getDefaultMessage()).collect(Collectors.toList());

        ExceptionPayloadDTO exceptionPayload = ExceptionPayloadDTO.builder()
                .timestamp(LocalDateTime.now())
                .title("Validation Error")
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .description(errorMessages.toArray(new String[errorMessages.size()]))
                .build();

        return ResponseEntity.badRequest().body(exceptionPayload);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    protected ResponseEntity<?> handleUsernameNotFoundException(UsernameNotFoundException exception) {
		ExceptionPayloadDTO exceptionPayload = ExceptionPayloadDTO.builder()
				.timestamp(LocalDateTime.now())
				.title("Usuário não encontrado")
				.statusCode(HttpStatus.UNAUTHORIZED.value())
				.description(new String[]{exception.getMessage()})
				.build();

		return new ResponseEntity<>(exceptionPayload, HttpStatus.UNPROCESSABLE_ENTITY);
	}

    @ExceptionHandler(AuthException.class)
    protected ResponseEntity<?> handleAuthException(AuthException exception) {
		ExceptionPayloadDTO exceptionPayload = ExceptionPayloadDTO.builder()
				.timestamp(LocalDateTime.now())
				.title(exception.getTitle())
				.statusCode(exception.getHttpStatus().value())
				.description(new String[]{exception.getMessage()})
				.build();

		return new ResponseEntity<>(exceptionPayload, exception.getHttpStatus());
	}

    @ExceptionHandler(ExpiredJwtException.class)
    protected ResponseEntity<?> handleExpiredJwtException(ExpiredJwtException exception) {
		ExceptionPayloadDTO exceptionPayload = ExceptionPayloadDTO.builder()
				.timestamp(LocalDateTime.now())
				.title("Token expirado")
				.statusCode(HttpStatus.UNAUTHORIZED.value())
				.description(new String[]{"Token fornecido é inválido"})
				.build();

		return new ResponseEntity<>(exceptionPayload, HttpStatus.UNAUTHORIZED);
	}

    @ExceptionHandler(MalformedJwtException.class)
    protected ResponseEntity<?> handleMalformedJwtException(MalformedJwtException exception) {
		ExceptionPayloadDTO exceptionPayload = ExceptionPayloadDTO.builder()
				.timestamp(LocalDateTime.now())
				.title("Formação de token inválida")
				.statusCode(HttpStatus.BAD_REQUEST.value())
				.description(new String[]{"Token fornecido não pôde ser lido"})
				.build();

		return new ResponseEntity<>(exceptionPayload, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(value = {RuntimeException.class})
	protected ResponseEntity<Object> handleRuntimeException(RuntimeException exception) {
		ExceptionPayloadDTO exceptionPayload = ExceptionPayloadDTO.builder()
			.timestamp(LocalDateTime.now())
			.title("Ocorreu um erro durante o processamento da requisição")
			.statusCode(HttpStatus.UNPROCESSABLE_ENTITY.value())
			.description(new String[]{exception.getMessage()})
			.build();

		return new ResponseEntity<>(exceptionPayload, HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@ExceptionHandler(value= {JpaSystemException.class})
	protected ResponseEntity<Object> handleIllegalArgumentException(JpaSystemException ex) {
		ExceptionPayloadDTO exceptionPayload = ExceptionPayloadDTO.builder()
				.timestamp(LocalDateTime.now())
				.title("Campo inválido")
				.statusCode(HttpStatus.CONFLICT.value())
				.description(new String[]{ex.getMostSpecificCause().getMessage()})
				.build();
		return new ResponseEntity<>(exceptionPayload, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(value= {DataIntegrityViolationException.class})
	protected ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {

		ExceptionPayloadDTO exceptionPayload = ExceptionPayloadDTO.builder()
				.timestamp(LocalDateTime.now())
				.title("Campo inválido")
				.statusCode(HttpStatus.CONFLICT.value())
				.description(new String[]{ex.getMostSpecificCause().getMessage()})
				.build();
		return new ResponseEntity<>(exceptionPayload, HttpStatus.CONFLICT);
	}
}
