package com.kwamina.bankApp;

public class Utility {

    public static boolean doesNotContainInvalidCharacters(String s) {
        return s.matches("[a-zA-Z]+");
    }


    public static void print(String s) {
        System.out.println(s);
    }


    public static void displayMessage(String type, String msg) {

        String body = "\n";
        int length = type.length() + msg.length() + 13;

        for (int i = 0; i < length; i++) {
            body += "-";
        }
        body += "\n";

        body += "|\t" + String.format("%5s", type.toUpperCase()) + String.format("%5s", "|") + "\t" + String.format("%5s", msg) + String.format("%2s", "|") + "\n";

        for (int i = 0; i < length; i++) {
            body += "-";
        }
        body += "\n";

        Utility.print(body);
    }


}
