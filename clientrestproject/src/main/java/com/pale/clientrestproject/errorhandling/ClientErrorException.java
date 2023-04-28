package com.pale.clientrestproject.errorhandling;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ClientErrorException extends Throwable{

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleInvalidDataError(MethodArgumentNotValidException exceptionErrors) {
        Map<String, String> handledErrorsMap = new HashMap<>();
        exceptionErrors.getBindingResult().getFieldErrors().forEach(error -> {
            handledErrorsMap.put(error.getField(), error.getDefaultMessage());
        });
        return handledErrorsMap;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ClientUniqueException.class)
    public Map<String, String> handleUniqueConstraintError(ClientUniqueException clientUniqueException) {
        Map<String, String> uniqueConstraintError = new HashMap<>();
        uniqueConstraintError.put("Client Error: ", clientUniqueException.getMessage());

        return uniqueConstraintError;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ClientNotFoundException.class)
    public Map<String, String> handleCleintNotFoundError(ClientNotFoundException clientNotFoundException) {
        Map<String, String> userNotFoundErrorMap = new HashMap<>();
        userNotFoundErrorMap.put("Client Error: ", clientNotFoundException.getMessage());

        return userNotFoundErrorMap;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidIDNumberException.class)
    public Map<String, String> handleUniqueConstraintError(InvalidIDNumberException invalidIDNumberException) {
        Map<String, String> invalidIDNumberError = new HashMap<>();
        invalidIDNumberError.put("Client Error: ", invalidIDNumberException.getMessage());

        return invalidIDNumberError;
    }
}
