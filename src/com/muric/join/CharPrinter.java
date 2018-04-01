package com.muric.join;

public class CharPrinter implements Runnable {
    private int num;
    private char cc;

    public CharPrinter(int num, char cc){
        this.num = num;
        this.cc = cc;
    }

    @Override
    public void run() {
        try {
            for (int ii = num; num > 0; num--) {
                System.out.print(cc);
                Thread.currentThread().sleep(100);
            }
            System.out.print(++cc);
        } catch (InterruptedException ie){
            System.err.println("Exception " + ie);
        }
    }
}
