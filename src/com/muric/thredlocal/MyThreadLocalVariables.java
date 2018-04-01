package com.muric.thredlocal;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class MyThreadLocalVariables {
    // Atomic integer containing the next thread ID to be assigned
    private static final AtomicInteger nextId   = new AtomicInteger(0);

    // Thread local variable containing each thread's ID
    public static final ThreadLocal<Integer> threadId = new ThreadLocal<Integer>()
    {
        @Override
        protected Integer initialValue()
        {
            return nextId.getAndIncrement();
        }
    };

    public static final ThreadLocal<Integer> anotherInteger = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue(){
            return (int)(Math.random() * 1000);
        }
    };

    // Returns the current thread's starting timestamp
    public static final ThreadLocal<Date> startDate = new ThreadLocal<Date>()
    {
        protected Date initialValue()
        {
            return new Date();
        }
    };
}
