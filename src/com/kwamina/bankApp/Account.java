package com.kwamina.bankApp;

public class Account {

    private int acctNumber;
    User acctOwner;
    String acctID;
    private double balance;

    Account(double initialBalance) {
        this.balance = initialBalance;

        Factory factory = Factory.getInstance();
        acctNumber = factory.generateAcctNumber();
        acctID = factory.generateUUID();

    }


    public void setOwner(User user) {
        this.acctOwner = user;
    }


    public User getOwner() {
        return this.acctOwner;
    }

    public String getOwnerID() {
        return this.acctOwner.getUserID();
    }

    public String getAcctID() {
        return this.acctID;
    }

    public Integer getAcctNum() {
        return this.acctNumber;
    }

    public synchronized double getBalance() {
        return this.balance;
    }


    public synchronized void deposit(double amount) {
        this.balance += amount;
    }


    public synchronized void withdraw(double amount) throws InsufficientFundsException {

        if (amount > balance) {
            throw new InsufficientFundsException("Insufficient funds to withdraw amount: " + amount + " from acctNum: " + acctNumber);
        }

        this.balance -= amount;
    }

    @Override
    public String toString() {
        return "Account{" +
                "acctNumber=" + acctNumber +
                ", acctOwner=" + acctOwner.getName() +
                ", acctID='" + acctID + '\'' +
                ", balance=" + balance +
                '}';
    }
}
