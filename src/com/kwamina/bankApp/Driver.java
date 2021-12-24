package com.kwamina.bankApp;

import java.util.Scanner;

public class Driver {

    public static void main(String [] args) throws UserExistsException {

        BankSystem bank = new BankSystem();

        bank.createUserAccount("Kwamina", 50);
        bank.createUserAccount("Ernest", 50);

        Scanner scan = new Scanner(System.in);
        String input = "";

        do {
            System.out.print("Enter command [Type in 'DONE' to terminate the program. Type in 'HELP' for commands] : ");
            input = scan.nextLine();

            if (input.equalsIgnoreCase("DONE")) {
                Utility.displayMessage("END", "Terminating program.");
            } else {

                Session.Tuple<Command, String []> tuple = Session.getCommand(input);
                Command cmd = tuple.key1;
                String [] tokens = tuple.key2;

                if (cmd == Command.INVALID) {
                    Utility.displayMessage("ERROR", "Invalid command entered.");
                    Session.showHelp();

                } else {
                    Session.processCommand(cmd, tokens, bank);
                }


            }

        } while (!input.equalsIgnoreCase("DONE"));

    }

}
