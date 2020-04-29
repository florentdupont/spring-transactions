package com.example.demo;

import static org.springframework.transaction.support.TransactionSynchronizationManager.isActualTransactionActive;

public class TimingUtils {

    public static void tooLongOperation() {
        System.out.println("tooLongOperation - active ? " + isActualTransactionActive());

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
