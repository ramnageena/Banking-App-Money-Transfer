package com.banking_app.utils;

import java.util.Random;

public class BankingUtils {
    private BankingUtils(){

    }
    public static Long random(){
        Random random = new Random();
       return 1_000_000_0000L + (long)(random.nextDouble() * 9_000_000_0000L);
    }

    public static Long transactionNumber() {
        Random random = new Random();
        // Generate a 12-digit random number
        return 1_000_000_000_000L + (long)(random.nextDouble() * 9_000_000_000_000L);
    }

}
