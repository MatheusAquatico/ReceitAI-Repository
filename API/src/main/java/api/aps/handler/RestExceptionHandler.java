package api.aps.handler;

import api.aps.exception.*;
import jakarta.annotation.Nullable;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<BadRequestExceptionDetails> handleBadRequestException(BadRequestException bre) {
        BadRequestExceptionDetails exceptionDetails = new BadRequestExceptionDetails(
                "Bad Request, cheque a documentação",
                HttpStatus.BAD_REQUEST.value(),
                bre.getMessage(),
                bre.getClass().getName(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(exceptionDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationExceptionDetails> handlerMethodArgumentNotValidException(
            MethodArgumentNotValidException exception) {
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        String fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(", "));
        String fieldsMessage = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(", "));
        ValidationExceptionDetails exceptionDetails = new ValidationExceptionDetails(
                "Bad Request, campos inválidos",
                HttpStatus.BAD_REQUEST.value(),
                exception.getMessage(),
                exception.getClass().getName(),
                LocalDateTime.now(),
                fields,
                fieldsMessage
        );
        return new ResponseEntity<>(exceptionDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ConstraintViolationExceptionDetails> handlerConstraintViolationException(
            ConstraintViolationException exception) {
        ConstraintViolationExceptionDetails exceptionDetails = new ConstraintViolationExceptionDetails(
                "Bad Request, violação de requisitos",
                HttpStatus.BAD_REQUEST.value(),
                exception.getMessage(),
                exception.getClass().getName(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(exceptionDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler()
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ExceptionDetails exceptionDetails = new ExceptionDetails(
                ex.getCause().getMessage(),
                status.value(),
                ex.getMessage(),
                ex.getClass().getName(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(exceptionDetails, headers, status);
    }
}
