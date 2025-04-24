package com.erp.Inventory.Management.config;

public class TokenValidationException extends RuntimeException {

    public TokenValidationException(String message) {
        super(message);  // Pass the message to the parent (RuntimeException)
    }
}

