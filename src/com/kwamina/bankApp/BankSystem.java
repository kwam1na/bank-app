package com.kwamina.bankApp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class BankSystem {

    private LocalDB db;

    private HashMap<User, HashSet<Account>> accounts;
    private HashMap<String, User> users;

    BankSystem() {
//        this.accounts = new HashMap<>();
//        this.users = new HashMap<>();
        db = new LocalDB();
    }

    public void createUserAccount(String username, double initialBalance) throws UserExistsException {

        db.createAccount(username, initialBalance);
    }

    public void addAccount(Account account, String userName) {

        User acctUser = users.get(userName);

        if (acctUser == null) {
            acctUser = new User(userName);
            users.put(userName, acctUser);
        }

        acctUser.addAccount(account);

        HashSet<Account> userAccounts = accounts.get(acctUser);

        if (userAccounts == null) {
            HashSet<Account> newAccounts = new HashSet<>();
            newAccounts.add(account);
            accounts.put(acctUser, newAccounts);

        } else {
            userAccounts.add(account);
            accounts.put(acctUser, userAccounts);

        }
    }


    public void changeUserName(String oldName, String newName) throws NoSuchUserException {

        User user = getUser(oldName);

        user.setName(newName);
        users.remove(oldName);
        users.put(newName, user);
    }


    public void withdraw(String userName, Integer acctNum, double amount) throws NoSuchUserException, InsufficientFundsException, InvalidAmountException, Exception {

        User user = getUser(userName);

        if (amount <= 0) {
            throw new InvalidAmountException("Only positive values can be withdrawn");
        }

        // Get the user's account to be withdrawn from
        Account account = user.getAccount(acctNum);

        // Handle case where the account with acctID does not exist
        if (account == null) {
            throw new Exception("An account with acctNum: " + acctNum + " does not exist for the user " + userName);
        }

        account.withdraw(amount);
    }


    public void transfer(String username1, Integer acctNum1, String username2, Integer acctNum2, double amount) throws NoSuchUserException, InsufficientFundsException, InvalidAmountException, Exception {

        withdraw(username1, acctNum1, amount);
        deposit(username2, acctNum2, amount);

    }


    public void deposit(String userName, Integer acctNum, double amount) throws NoSuchUserException, InvalidAmountException, Exception {

        User user = getUser(userName);

        if (amount <= 0) {
            throw new InvalidAmountException("Only positive values can be deposited");
        }

        // Get the user's account to be withdrawn from
        Account account = user.getAccount(acctNum);

        // Handle case where the account with acctID does not exist
        if (account == null) {
            throw new Exception("An account with acctNum: " + acctNum + " does not exist for the user " + userName);
        }

        account.deposit(amount);

    }


    private User getUser(String userName) throws NoSuchUserException {

        User user = users.get(userName);

        if (user == null) {
            throw new NoSuchUserException("No user by the name " + userName + " exists.");
        }

        return user;
    }

    @Override
    public String toString() {
        return "BankSystem{" +
                "accounts=" + accounts +
                '}';
    }

    public String displayUsers() {
        String res = "\n-----------------------------------------------------\n";
        res += "|\t" + String.format("%5s", "USERS") + String.format("%19s", "|") + "\t" + String.format("%5s", "ACCOUNTS") + String.format("%13s", "|") + "\n";
        res += "-----------------------------------------------------\n";

        for (String user: users.keySet()) {

            res += "|\t" + String.format("%1s", user) + String.format("%" + (24 - user.length()) + "s", "|");
            for (Integer accountNum: users.get(user).getAccounts()) {
                res += "\tAcct num: " + accountNum + "\t|\n";
            }
        }

        res += "-----------------------------------------------------\n";
        return res;
    }

    public String displayUserAccount(String username) throws NoSuchUserException {

        User user = getUser(username);

        return user.displayAccount();
    }

}
