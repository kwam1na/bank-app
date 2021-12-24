package com.kwamina.bankApp;

public class UserExistsException extends Exception {

    UserExistsException(String errorMessage) {
        super(errorMessage);
    }
}
