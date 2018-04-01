package com.muric.thredlocal;

import java.util.concurrent.TimeUnit;

/**
 * Contains Runnable for the thread
 */
class DemoTask implements Runnable
{
    @Override
    public void run()
    {
        System.out.printf("Starting Thread [%s], id = %d, started on %s, anotherInt = %d\n",
                Thread.currentThread().getName(), MyThreadLocalVariables.threadId.get(),
                MyThreadLocalVariables.startDate.get(), MyThreadLocalVariables.anotherInteger.get());

        try
        {
            TimeUnit.SECONDS.sleep((int) Math.rint(Math.random() * 10));
            Service service = new Service();
            service.serviceMethod();
            TimeUnit.SECONDS.sleep((int) Math.rint(Math.random() * 10));
            MyThreadLocalVariables.anotherInteger.set(MyThreadLocalVariables.anotherInteger.get() + 10000);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        System.out.printf("Thread Finished [%s], id = %d, started on %s, anotherInt = %d\n",
                Thread.currentThread().getName(), MyThreadLocalVariables.threadId.get(),
                MyThreadLocalVariables.startDate.get(), MyThreadLocalVariables.anotherInteger.get());
    }
}