package com.kwamina.bankApp;

public class InsufficientFundsException extends Exception {

    InsufficientFundsException(String errorMessage) {
        super(errorMessage);
    }

}
