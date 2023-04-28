package com.pale.clientrestproject.errorhandling;

import lombok.Data;

@Data
public class ClientNotFoundException extends Exception {

    public ClientNotFoundException(String message) {
        super(message);
    }
}
