package com.kwamina.bankApp;

public interface Database {

    public void createAccount(String username, double initialBalance) throws UserExistsException;
    public void createUser(String username, User user);
    public void modifyAccount(User user, Account account);
    public User getUser(String username) throws NoSuchUserException;
    public String[] getUsers();

}
