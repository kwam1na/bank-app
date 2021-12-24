package com.kwamina.bankApp;

import jdk.jshell.execution.Util;

public class Session {

    public static class Tuple<X, Y> {
        public final X key1;
        public final Y key2;

        public Tuple(X key1, Y key2) {
            this.key1 = key1;
            this.key2 = key2;
        }
    }

    private static final String CREATE_ACCOUNT = "create-account";
    private static final String WITHDRAW = "withdraw";
    private static final String DEPOSIT = "deposit";
    private static final String TRANSFER = "transfer";
    private static final String DISPLAY_USERS = "display-users";
    private static final String DISPLAY_BANK = "display-bank";
    private static final String DISPLAY_ACCOUNT = "display-account";
    private static final String HELP = "help";

    private static final String CREATE_CMD = "create-account <username> <initialAmount>";
    private static final String WITHDRAW_CMD = "withdraw <username> <acctNum> <amount>";
    private static final String DEPOSIT_CMD = "deposit <username> <acctNum> <amount>";
    private static final String TRANSFER_CMD = "transfer <username1> <accountNum1> <username2> <accountNum2> <amount>";
    private static final String DISPLAY_ACCOUNT_CMD = "display-account <username>";

    private static final String INVALID_PARAMS_CREATE_ACCOUNT = "Expected: " + CREATE_CMD;
    private static final String INVALID_PARAMS_WITHDRAW = "Expected: " + WITHDRAW_CMD;
    private static final String INVALID_PARAMS_DEPOSIT = "Expected: " + DEPOSIT_CMD;
    private static final String INVALID_PARAMS_TRANSFER = "Expected: " + TRANSFER_CMD;
    private static final String INVALID_PARAMS_DISPLAY_ACCOUNT = "Expected: " + DISPLAY_ACCOUNT_CMD;

    public static Tuple<Command, String []> getCommand(String input) {

        String [] tokens = input.split(" ");

        if (tokens.length == 0) {
            return new Tuple<>(Command.INVALID, null);
        }

        String command = tokens[0];

        switch (command.toLowerCase()) {

            case CREATE_ACCOUNT:
                return new Tuple<>(Command.CREATE_ACCOUNT, tokens);

            case WITHDRAW:
                return new Tuple<>(Command.WITHDRAW, tokens);

            case DEPOSIT:
                return new Tuple<>(Command.DEPOSIT, tokens);

            case TRANSFER:
                return new Tuple<>(Command.TRANSFER, tokens);

            case DISPLAY_ACCOUNT:
                return new Tuple<>(Command.DISPLAY_ACCOUNT, tokens);

            case DISPLAY_USERS:
                return new Tuple<>(Command.DISPLAY_USERS, null);

            case DISPLAY_BANK:
                return new Tuple<>(Command.DISPLAY_BANK, null);

            case HELP:
                return new Tuple<>(Command.HELP, null);

            default:
                return new Tuple<>(Command.INVALID, null);
        }
    }


    public static void processCommand(Command command, String[] tokens, BankSystem bank) {

        switch (command) {

            case CREATE_ACCOUNT:
                handleCreateAccount(tokens, bank);
                break;

            case DISPLAY_ACCOUNT:
                handleDisplayUserAccount(tokens, bank);
                break;

            case DISPLAY_USERS:
                Utility.print(bank.displayUsers());
                break;

            case DEPOSIT:
                handleDeposit(tokens, bank);
                break;

            case WITHDRAW:
                handleWithdraw(tokens, bank);
                break;

            case TRANSFER:
                handleTransfer(tokens, bank);
                break;

            case HELP:
                showHelp();
                break;
        }
    }


    private static void handleCreateAccount(String [] tokens, BankSystem bank) {

        // Validate tokens. Tokens must be <cmd> <username> <initialAmount>
        if (tokens.length != 3) {
            Utility.displayMessage("ERROR", INVALID_PARAMS_CREATE_ACCOUNT);
            return;
        }

        // Validate username. Username must only contain letters
        if (!Utility.doesNotContainInvalidCharacters(tokens[1])) {
            Utility.displayMessage("ERROR", "User name must only contain letters");
            return;
        }

        // Validate initial balance
        try {
            Integer initialBalance = Integer.parseInt(tokens[2]);

            if (initialBalance <= 0) {
                Utility.displayMessage("ERROR", "Initial balance must be a non-negative number greater than 0");
                return;
            }

            // Valid command sent in. Proceed to create the user
            bank.createUserAccount(tokens[1], initialBalance);
            Utility.displayMessage("SUCCESS", "Account for user " + tokens[1] + " created successfully");


        } catch (NumberFormatException e) {
            Utility.displayMessage("ERROR", "Initial balance must be a non-negative number greater than 0");
        } catch (UserExistsException e) {
            Utility.displayMessage("ERROR", e.getMessage());
        }
    }


    private static void handleDisplayUserAccount(String [] tokens, BankSystem bank) {

        if (tokens.length != 2) {
            Utility.displayMessage("ERROR", INVALID_PARAMS_DISPLAY_ACCOUNT);
            return;
        }

        String username = tokens[1];

        // Validate username. Username must only contain letters
        if (!Utility.doesNotContainInvalidCharacters(username)) {
            Utility.displayMessage("ERROR", "User name must only contain letters");
            return;
        }

        try {
            String accountDetails = bank.displayUserAccount(username);
            Utility.print(accountDetails);
        } catch (NoSuchUserException e) {
            Utility.displayMessage("ERROR", e.getMessage());
        }
    }


    private static void handleDeposit(String [] tokens, BankSystem bank) {

        // Validate tokens. Tokens must be <cmd> <username> <amount>
        if (tokens.length != 4) {
            Utility.displayMessage("ERROR", INVALID_PARAMS_DEPOSIT);
            return;
        }

        String username = tokens[1];

        // Validate username. Username must only contain letters
        if (!Utility.doesNotContainInvalidCharacters(username)) {
            Utility.displayMessage("ERROR", "User name must only contain letters");
            return;
        }

        // Validate initial balance
        try {
            Integer amount = Integer.parseInt(tokens[3]);

            if (amount <= 0) {
                Utility.displayMessage("ERROR", "Amount must be a non-negative number greater than 0");
                return;
            }

            // Valid command sent in.
            Integer acctNum = Integer.parseInt(tokens[2]);
            bank.deposit(username, acctNum, amount);
            Utility.displayMessage("SUCCESS", amount + " has been added to " + username + "'s account.");


        } catch (NumberFormatException e) {
            Utility.displayMessage("ERROR", "Account number or amount to be deposited must be a non-negative number greater than 0");
        } catch (Exception e) {
            Utility.displayMessage("ERROR", e.getMessage());
        }
    }


    private static void handleWithdraw(String [] tokens, BankSystem bank) {

        // Validate tokens. Tokens must be <cmd> <username> <amount>
        if (tokens.length != 4) {
            Utility.displayMessage("ERROR", INVALID_PARAMS_WITHDRAW);
            return;
        }

        String username = tokens[1];

        // Validate username. Username must only contain letters
        if (!Utility.doesNotContainInvalidCharacters(username)) {
            Utility.displayMessage("ERROR", "User name must only contain letters");
            return;
        }


        try {
            Integer amount = Integer.parseInt(tokens[3]);

            if (amount <= 0) {
                Utility.displayMessage("ERROR", "Amount must be a non-negative number greater than 0");
                return;
            }

            // Valid command sent in.
            Integer acctNum = Integer.parseInt(tokens[2]);

            bank.withdraw(username, acctNum, amount);
            Utility.displayMessage("SUCCESS", amount + " has been withdrawn from " + username + "'s account.");


        } catch (NumberFormatException e) {
            Utility.displayMessage("ERROR", "Account number or amount to be withdrawn must be a non-negative number greater than 0");
        }  catch (Exception e) {
            Utility.displayMessage("ERROR", e.getMessage());
        }
    }


    private static void handleTransfer(String [] tokens, BankSystem bank) {

        // Validate tokens. Tokens must be <cmd> <username1> <accountNum1> <username2> <accountNum2> <amount>
        if (tokens.length != 6) {
            Utility.displayMessage("ERROR", INVALID_PARAMS_TRANSFER);
            return;
        }

        String username1 = tokens[1];
        String acctNum1Str = tokens[2];

        String username2 = tokens[3];
        String acctNum2Str = tokens[4];

        // Validate username. Username must only contain letters
        if (!Utility.doesNotContainInvalidCharacters(username1)) {
            Utility.displayMessage("ERROR", "User name must only contain letters");
            return;
        }

        if (!Utility.doesNotContainInvalidCharacters(username2)) {
            Utility.displayMessage("ERROR", "User name must only contain letters");
            return;
        }

        // Validate initial balance
        try {
            Integer amount = Integer.parseInt(tokens[5]);

            if (amount <= 0) {
                Utility.displayMessage("ERROR", "Amount must be a non-negative number greater than 0");
                return;
            }

            // Valid command sent in.
            Integer acctNum1 = Integer.parseInt(acctNum1Str);
            Integer acctNum2 = Integer.parseInt(acctNum2Str);
            bank.transfer(username1, acctNum1, username2, acctNum2, amount);

            Utility.displayMessage("SUCCESS", amount + " was successfully transferred from " + username1 + " to " + username2);

        } catch (NumberFormatException e) {
            Utility.displayMessage("ERROR", "Account number or amount to be withdrawn must be a non-negative number greater than 0");
        } catch (Exception e) {
            Utility.displayMessage("ERROR", e.getMessage());
        }

    }


    public static void showHelp() {

        String help = "\n-----------------------------------------------------------------------------\n";
        help += "|\t" + String.format("%25s", "COMMANDS") + String.format("%49s", "|\n");
        help += "-----------------------------------------------------------------------------\n";

        help += "|\t" + String.format("%1s", CREATE_CMD) + String.format("%" + (74 - CREATE_CMD.length()) + "s", "|\n");

        help += "|\t" + String.format("%1s", WITHDRAW_CMD) + String.format("%" + (74 - WITHDRAW_CMD.length()) + "s", "|\n");

        help += "|\t" + String.format("%1s", DEPOSIT_CMD) + String.format("%" + (74 - DEPOSIT_CMD.length()) + "s", "|\n");

        help += "|\t" + String.format("%1s", TRANSFER_CMD) + String.format("%" + (74 - TRANSFER_CMD.length()) + "s", "|\n");

        help += "|\t" + String.format("%1s", DISPLAY_USERS) + String.format("%" + (74 - DISPLAY_USERS.length()) + "s", "|\n");

        help += "|\t" + String.format("%1s", DISPLAY_ACCOUNT_CMD) + String.format("%" + (74 - DISPLAY_ACCOUNT_CMD.length()) + "s", "|\n");

        help += "-----------------------------------------------------------------------------\n";

        Utility.print(help);
    }

}
