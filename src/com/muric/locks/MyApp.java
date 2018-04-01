package com.muric.locks;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MyApp {
    public static void main(String[] args){
        Account account = new Account();
        ExecutorService pool = Executors.newFixedThreadPool(10);

        pool.submit(new AccountAccess(account, AccessType.READ, .0, 50));
        pool.submit(new AccountAccess(account, AccessType.ADD, 50.0, 200));
        pool.submit(new AccountAccess(account, AccessType.ADD, 100.0, 300));
        pool.submit(new AccountAccess(account, AccessType.ADD, 300.0, 500));
        pool.submit(new AccountAccess(account, AccessType.SUBTRACT, 20.0, 300));
        pool.submit(new AccountAccess(account, AccessType.SUBTRACT, 30.0, 400));
        pool.submit(new AccountAccess(account, AccessType.READ, 0.0, 500));
        pool.submit(new AccountAccess(account, AccessType.SUBTRACT, 50.0, 600));
        pool.submit(new AccountAccess(account, AccessType.READ, 0.0, 100));
        pool.submit(new AccountAccess(account, AccessType.SUBTRACT, 60.0, 700));

        try {
            Thread.currentThread().sleep(5000);
            pool.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException ie){}
    }
}
