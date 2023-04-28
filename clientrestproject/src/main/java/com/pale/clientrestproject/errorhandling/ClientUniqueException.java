package com.pale.clientrestproject.errorhandling;


import org.springframework.dao.DataIntegrityViolationException;

public class ClientUniqueException extends DataIntegrityViolationException {
    public ClientUniqueException(String message) {
        super(message);
    }
}
