package com.twa.reservations.exception;

import com.twa.reservations.dto.ErrorDTO;
import com.twa.reservations.enums.APIError;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class APIExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EdteamException.class)
    public ResponseEntity<ErrorDTO> duplicateResource(EdteamException e, WebRequest request) {
        return ResponseEntity.status(e.getStatus()).body(new ErrorDTO(e.getDescription(), e.getReasons()));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<String> reasons = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            reasons.add(String.format("%s - %s", error.getField(), error.getDefaultMessage()));
        }
        return ResponseEntity.status(APIError.VALIDATION_ERROR.getHttpStatus())
                .body(new ErrorDTO(APIError.VALIDATION_ERROR.getMessage(), reasons));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(ConstraintViolationException ex, WebRequest request) {
        List<String> reasons = new ArrayList<>();
        for (ConstraintViolation error : ex.getConstraintViolations()) {
            reasons.add(String.format("%s - %s", error.getPropertyPath(), error.getMessage()));
        }
        return ResponseEntity.status(APIError.VALIDATION_ERROR.getHttpStatus())
                .body(new ErrorDTO(APIError.VALIDATION_ERROR.getMessage(), reasons));
    }

}
