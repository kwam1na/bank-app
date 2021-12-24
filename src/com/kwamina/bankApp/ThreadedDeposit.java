package com.kwamina.bankApp;

public class ThreadedDeposit extends Thread{

    BankSystem bank;
    String userName;
    Integer acctNum;
    double amount;

    ThreadedDeposit(BankSystem bank, String userName, Integer acctNum, double amount) {
        this.bank = bank;
        this.userName = userName;
        this.acctNum = acctNum;
        this.amount = amount;
    }

    public void run() {
        try {
            bank.deposit(userName, acctNum, amount);
        } catch (Exception e) {
            Utility.displayMessage("ERROR", e.getMessage());
        }
    }
}
