package com.kwamina.bankApp;

import java.util.HashMap;
import java.util.HashSet;

public class LocalDB implements Database {

    private HashMap<User, HashSet<Account>> accounts;
    private HashMap<String, User> users;

    LocalDB() {
        this.accounts = new HashMap<>();
        this.users = new HashMap<>();
    }

    @Override
    public void createAccount(String username, double initialBalance) throws UserExistsException {

        if (users.containsKey(username)) {
            throw new UserExistsException("A user by the name " + username + " already exists in the system.");
        }
        User user = new User(username);
        Account account = new Account(initialBalance);

        // Add this user to all users
        users.put(username, user);

        // Set the owner of the account to the user
        user.addAccount(account);

        // Add this account to accounts in the bank
        HashSet<Account> userAccounts = new HashSet<>();
        userAccounts.add(account);
        accounts.put(user, userAccounts);
    }


    @Override
    public void modifyAccount(User user, Account account) {

    }

    @Override
    public User getUser(String username) throws NoSuchUserException {
        User user = users.get(username);

        if (user == null) {
            throw new NoSuchUserException("No user by the name " + username + " exists.");
        }

        return user;
    }

    @Override
    public String[] getUsers() {
        return users.keySet().toArray(new String[0]);
    }

}
