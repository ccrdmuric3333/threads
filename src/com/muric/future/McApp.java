package com.muric.future;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class McApp {
    private static final McApp app = new McApp();
    private static final int NUM_THREADS = 5;

    public static void main(String[] args) {
        app.run();
    }

    private void run(){
        ExecutorService service = Executors.newFixedThreadPool(NUM_THREADS);
        List<Callable<Factorial>> tasks = new ArrayList<>();
        List<Future<Factorial>> results = null;

        for(int ii = 0; ii < NUM_THREADS * 2; ii++){
            tasks.add(new CalculateFactorial(new Factorial ((int)(Math.random() * 28), (long) (Math.random() * 100))));
        }

        try {
            System.out.println("Before invoking");
            results = service.invokeAll(tasks);
            System.out.println("After invoking");
        } catch (Exception ee){
            System.out.println("Exception: " + ee);
        } finally {
            System.out.println("Printing results");
            for(Future ff : results){
                try {
                    Factorial factorial = (Factorial)ff.get();
                    System.out.println(factorial);
                    System.out.println("Resutlt=" + factorial.getResult());
                } catch (Exception ee){
                    System.out.println("Exception: " + ee);
                }
            }
        }
        service.shutdown();
    }
}
