package com.kwamina.bankApp;

public class ThreadedTransfer {

    BankSystem bank;
    String userName1;
    Integer acctNum1;
    String userName2;
    Integer acctNum2;
    double amount;

    ThreadedTransfer(BankSystem bank, String userName1, Integer acctNum1, String userName2, Integer acctNum2, double amount) {
        this.bank = bank;
        this.userName1 = userName1;
        this.userName2 = userName2;
        this.acctNum1 = acctNum1;
        this.acctNum2 = acctNum2;
        this.amount = amount;
    }

    public void run() {
        try {
            bank.transfer(userName1, acctNum1, userName2, acctNum2, amount);
        } catch (Exception e) {
            Utility.displayMessage("ERROR", e.getMessage());
        }
    }


}
