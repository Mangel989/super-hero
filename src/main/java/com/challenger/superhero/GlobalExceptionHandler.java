package com.challenger.superhero;

import com.challenger.superhero.Commons.Converter;
import com.challenger.superhero.Exceptions.DataInputException;
import com.challenger.superhero.Exceptions.ErrorMessages;
import com.challenger.superhero.Exceptions.FailedVerificationException;
import com.challenger.superhero.Exceptions.Model.ApiExceptionResponse;
import com.challenger.superhero.Exceptions.NotFoundException;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.nio.file.AccessDeniedException;
import java.time.format.DateTimeParseException;
import java.util.HashMap;


@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<ApiExceptionResponse> DateTimeParseException(DateTimeParseException exception) {
        return this.registerException(exception, HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiExceptionResponse> exceptionHandler(Exception exception) {
        return this.registerException(exception, HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
    }

    @ExceptionHandler(FailedVerificationException.class)
    public ResponseEntity<ApiExceptionResponse> failedVerificationExceptionHandler(FailedVerificationException exception) {
        return this.registerException(exception, HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(DataInputException.class)
    public ResponseEntity<ApiExceptionResponse> dataInputExceptionHandler(DataInputException exception) {
        return this.registerException(exception, HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiExceptionResponse> notFoundExceptionHandler(NotFoundException exception) {
        return this.registerException(exception, HttpStatus.NOT_FOUND, exception.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiExceptionResponse> accessDeniedExceptionExceptionHandler(AccessDeniedException exception) {
        return this.registerException(exception, HttpStatus.UNAUTHORIZED, exception.getMessage());
    }

   /* @ExceptionHandler(EmailNotSentException.class)
    public ResponseEntity<ApiExceptionResponse> emailNotSentExceptionHandler(EmailNotSentException exception) {
        return this.registerException(exception, HttpStatus.GATEWAY_TIMEOUT, exception.getMessage());
    }*/

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiExceptionResponse> entityNotFoundExceptionExceptionHandler(EntityNotFoundException exception) {
        return this.registerException(exception, HttpStatus.NOT_FOUND, ErrorMessages.REGISTER_NOT_FOUND);
    }

    @ExceptionHandler(InvalidDataAccessApiUsageException.class)
    public ResponseEntity<ApiExceptionResponse> invalidDataAccessApiUsageExceptionHandler(InvalidDataAccessApiUsageException exception) {
        return this.registerException(exception, HttpStatus.BAD_REQUEST, ErrorMessages.ARGUMENT_INVALID);
    }

    private ResponseEntity<ApiExceptionResponse> registerException(Exception exception, HttpStatus httpStatus, String message) {
        long ownId = System.currentTimeMillis();
        HashMap<String, Object> hashMap = new HashMap<>();

        hashMap.put("OwnID", ownId);
        hashMap.put("Exception", exception.getMessage());
        log.error(Converter.convertValue(hashMap));

        ApiExceptionResponse error = new ApiExceptionResponse(httpStatus);
        error.setMessage(message);

        //exception.printStackTrace();
        return new ResponseEntity<>(error, httpStatus);
    }
}

