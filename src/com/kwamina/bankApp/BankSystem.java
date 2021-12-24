package com.kwamina.bankApp;

public class BankSystem {

    private LocalDB db;

    BankSystem() {
        db = new LocalDB();
    }

    public void createUserAccount(String username, double initialBalance) throws UserExistsException {
        db.createAccount(username, initialBalance);
    }

    
    public void withdraw(String username, Integer acctNum, double amount) throws NoSuchUserException, InsufficientFundsException, InvalidAmountException, Exception {

        User user = db.getUser(username);

        if (amount <= 0) {
            throw new InvalidAmountException("Only positive values can be withdrawn");
        }

        // Get the user's account to be withdrawn from
        Account account = user.getAccount(acctNum);

        // Handle case where the account with acctID does not exist
        if (account == null) {
            throw new Exception("An account with acctNum: " + acctNum + " does not exist for the user " + username);
        }

        account.withdraw(amount);
    }


    public void transfer(String username1, Integer acctNum1, String username2, Integer acctNum2, double amount) throws NoSuchUserException, InsufficientFundsException, InvalidAmountException, Exception {

        withdraw(username1, acctNum1, amount);
        deposit(username2, acctNum2, amount);
    }


    public void deposit(String username, Integer acctNum, double amount) throws NoSuchUserException, InvalidAmountException, Exception {

        User user = db.getUser(username);

        if (amount <= 0) {
            throw new InvalidAmountException("Only positive values can be deposited");
        }

        // Get the user's account to be deposited to
        Account account = user.getAccount(acctNum);

        // Handle case where the account with acctID does not exist
        if (account == null) {
            throw new Exception("An account with acctNum: " + acctNum + " does not exist for the user " + username);
        }

        account.deposit(amount);
    }
    

    public String displayUsers() {
        String res = "\n-----------------------------------------------------\n";
        res += "|\t" + String.format("%5s", "USERS") + String.format("%19s", "|") + "\t" + String.format("%5s", "ACCOUNTS") + String.format("%13s", "|") + "\n";
        res += "-----------------------------------------------------\n";


        for (String user: db.getUsers()) {

            res += "|\t" + String.format("%1s", user) + String.format("%" + (24 - user.length()) + "s", "|");
            try {
                for (Integer accountNum: db.getUser(user).getAccounts()) {
                    res += "\tAcct num: " + accountNum + "\t|\n";
                }
            } catch (NoSuchUserException e) {
                Utility.displayMessage("ERROR", e.getMessage());
            }
        }

        res += "-----------------------------------------------------\n";
        return res;
    }

    public String displayUserAccount(String username) throws NoSuchUserException {

        User user = db.getUser(username);
        return user.displayAccount();
    }

}
