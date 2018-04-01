package com.muric.join;

public class MyApp {
    public static void main(String[] args){
        Thread[] threads = new Thread[3];

        threads[0] = new Thread(new CharPrinter(10, 'a'), "Mc-A");
        threads[1] = new Thread(new CharPrinter(20, 'd'), "Mc-D");
        threads[2] = new Thread(new CharPrinter(30, 'g'), "Mc-G");

        for(int ii=0; ii<3; ii++){
            threads[ii].start();
            try{
                // Current thread will wait till ii Thread is finished
                threads[ii].join();
            } catch (InterruptedException ie){
                System.err.println("Exception " + ie);
            }
        }
    }
}
