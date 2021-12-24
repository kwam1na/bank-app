package com.kwamina.bankApp;

public class ThreadedWithdraw extends Thread {

    BankSystem bank;
    String userName;
    Integer acctNum;
    double amount;

    ThreadedWithdraw(BankSystem bank, String userName, Integer acctNum, double amount) {
        this.bank = bank;
        this.userName = userName;
        this.acctNum = acctNum;
        this.amount = amount;
    }

    public void run() {
        try {
            bank.withdraw(userName, acctNum, amount);
            Utility.displayMessage("SUCCESS", amount + " has been withdrawn from " + userName + "'s account.");
        } catch (Exception e) {
            Utility.displayMessage("ERROR", e.getMessage());
        }
    }
}
