package com.muric.thredlocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(5);

        for(int ii=0; ii<5; ii++){
            try {
                pool.execute(new DemoTask());
                Thread.currentThread().sleep(1000);
            } catch (Exception ee){
                //
            }
        }
        pool.shutdown();
    }
}
