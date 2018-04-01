package com.muric.future;

import java.util.concurrent.Callable;

/**
 * Created by muric on 10/20/2016.
 */
public class CalculateFactorial implements Callable<Factorial> {
    private Factorial factorial;

    public CalculateFactorial(Factorial factorial){
        this.factorial = factorial;
    }

    @Override
    public Factorial call() throws Exception {
        for(int ii = factorial.getSeed() - 1; ii > 1; ii--){
            factorial.setResult(Math.multiplyExact(factorial.getResult(), ii));
            Thread.currentThread().sleep(factorial.getDelay());
            System.out.println("Finished iteration " + ii + " on thread " + Thread.currentThread().getName());
        }
        return factorial;
    }
}
