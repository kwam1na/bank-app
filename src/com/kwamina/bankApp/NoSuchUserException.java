package com.kwamina.bankApp;

public class NoSuchUserException extends Exception {

    NoSuchUserException(String errorMessage) {
        super(errorMessage);
    }
}
