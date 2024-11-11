package com.banking_app.utils;

import java.util.Random;

public class BankingUtils {
    private BankingUtils(){

    }
    public static Long random(){
        Random random = new Random();
       return 1_000_000_0000L + (long)(random.nextDouble() * 9_000_000_0000L);
    }


}
