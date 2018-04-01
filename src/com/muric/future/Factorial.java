package com.muric.future;

public class Factorial {
    private int seed;
    private long delay;
    private long result;

    public Factorial(int seed, long delay){
        this.seed = seed;
        this.delay = delay;
        this.result = seed;
    }

    public int getSeed() {
        return seed;
    }

    public long getDelay() {
        return delay;
    }

    public long getResult() {
        return result;

    }

    public void setResult(long result) throws Exception{
        this.result = result;
    }

    @Override
    public String toString(){
        return "Factorial with seed=" + seed + " and delay=" + delay;
    }
}
