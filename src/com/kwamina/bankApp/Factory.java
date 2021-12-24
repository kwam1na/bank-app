package com.kwamina.bankApp;

import java.util.Random;

public class Factory {

    private static Factory instance = null;
    private static Random rand;
    private final int UUID_LENGTH = 10;

    private Factory() {
        rand = new Random();
    }

    public static Factory getInstance() {
        if (instance == null) {
            instance = new Factory();
        }
        return instance;
    }


    public int generateAcctNumber() {

        String pool = "123456789";
        String acctNum = "";

        for (int i = 0; i < 6; i++) {
            int index = rand.nextInt(pool.length());
            acctNum += pool.charAt(index);
        }

        return Integer.parseInt(acctNum);
    }


    public String generateUUID() {

        String uuid = "";
        String pool = "abcdefghijklmnopqrstuvwxyz0123456789";

        for (int i = 0; i < instance.UUID_LENGTH; i++) {
            int index = rand.nextInt(pool.length());
            uuid += pool.charAt(index);
        }

        return uuid;
    }



}
