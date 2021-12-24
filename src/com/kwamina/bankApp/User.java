package com.kwamina.bankApp;
import java.util.ArrayList;
import java.util.HashMap;

public class User {

    private String name;
    private HashMap<Integer, Account> accounts;
    private String userID;

    User(String name) {
        this.name = name;
        this.accounts = new HashMap<>();

        Factory factory = Factory.getInstance();
        userID = factory.generateUUID();
    }

    public String getName() {
        return this.name;
    }

    public String getUserID() {
        return this.userID;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public void addAccount(Account account) {
        account.setOwner(this);
        accounts.put(account.getAcctNum(), account);
    }

    public Account getAccount(Integer acctNum) {
        return accounts.get(acctNum);
    }


    public Integer [] getAccounts() {
        return accounts.keySet().toArray(new Integer[0]);
    }


    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", accounts=" + accounts +
                ", userID='" + userID + '\'' +
                '}';
    }

    public String displayAccount() {
        String res = "\n-----------------------------------------------------\n";
        res += "|\t" + String.format("%5s", "ACCOUNT NUMBER") + String.format("%15s", "|") + "\t" + String.format("%5s", "BALANCE") + String.format("%10s", "|") + "\n";
        res += "-----------------------------------------------------\n";

        for (Integer acctNum: accounts.keySet()) {
            res += "|\t" + String.format("%1s", acctNum) + String.format("%" + (20 - acctNum.toString().length()) + "s", "\t\t\t|");
            Double balance = accounts.get(acctNum).getBalance();
//            res += "\t" + balance + "\t\t\t|\n";
            res += "\t" + String.format("%1s", balance) + String.format("%" + (18 - balance.toString().length()) + "s", "|\n");
        }

        res += "-----------------------------------------------------\n";
        return res;
    }
}
