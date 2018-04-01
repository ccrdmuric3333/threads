package com.muric.locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class AccountAccess implements Runnable{
    private Account account;
    private AccessType type;
    private Double amount;
    private long delay;

    public AccountAccess(Account account, AccessType type, Double amount, long delay) {
        this.account = account;
        this.type = type;
        this.amount = amount;
        this.delay = delay;
    }

    @Override
    public void run() {
        ReadWriteLock lock = new ReentrantReadWriteLock();
        Lock readLock = lock.readLock();
        Lock writeLock = lock.writeLock();

        while (true) {
            try {
                switch (type) {
                    case ADD:
                        System.out.printf("%8s [%s] %2f [%tT] - %s\n", type.toString(), Thread.currentThread().getName(), amount, System.currentTimeMillis(), "attempt to add");
                        writeLock.lock();
                        account.add(amount);
                        Thread.currentThread().sleep(delay);
                        System.out.printf("%8s [%s] %2f [%tT] - %s\n", type.toString(), Thread.currentThread().getName(), .0, System.currentTimeMillis(), account.toString());
                        writeLock.unlock();
                        break;
                    case SUBTRACT:
                        try {
                            System.out.printf("%8s [%s] %2f [%tT] - %s\n", type.toString(), Thread.currentThread().getName(), amount, System.currentTimeMillis(), "attempt to subtract");
                            writeLock.lock();
                            account.subtract(amount);
                            Thread.currentThread().sleep(delay);
                            System.out.printf("%8s [%s] %2f [%tT] - %s\n", type.toString(), Thread.currentThread().getName(), .0, System.currentTimeMillis(), account.toString());
                        } catch (OutOfFundsException oofe){
                            System.err.println(type.toString() + " [" + Thread.currentThread().getName() + "], " + oofe.getMessage());
                        } finally {
                            writeLock.unlock();
                        }
                        break;
                    case READ:
                    default:
                        System.out.printf("%8s [%s] %2f [%tT] - %s\n", type.toString(), Thread.currentThread().getName(), amount, System.currentTimeMillis(), "attempt to read");
                        readLock.lock();
                        Thread.currentThread().sleep(delay);
                        System.out.printf("%8s [%s] %2f [%tT] - %s\n", type.toString(), Thread.currentThread().getName(), .0, System.currentTimeMillis(), account.toString());
                        readLock.unlock();
                        break;
                }
            } catch (InterruptedException ie) {
                System.err.println(type.toString() + " [" + Thread.currentThread().getName() + "] was iterrupted");
            } catch (Exception ee){
                System.err.println(type.toString() + " [" + Thread.currentThread().getName() + "] Exception: " + ee);
            }
        }
    }
}
