package com.muric.thredlocal;

public class Service {
    public void serviceMethod(){
        System.out.printf("SERVICE: [%s], id = %d, started on %s, anotherInt = %d\n",
                Thread.currentThread().getName(), MyThreadLocalVariables.threadId.get(),
                MyThreadLocalVariables.startDate.get(), MyThreadLocalVariables.anotherInteger.get());
    }
}
