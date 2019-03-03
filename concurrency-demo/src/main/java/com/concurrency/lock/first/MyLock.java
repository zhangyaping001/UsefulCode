package com.concurrency.lock.first;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Created by zhangyaping on 19/3/3.
 */
public class MyLock implements Lock {

    private boolean isHoldLock = false;

    //当前持有锁的线程
    private Thread holdLockThread = null;

    //重入次数
    private int reentryCount = 0;


    //支持可重入
    @Override
    public synchronized void lock() {
        if (isHoldLock && Thread.currentThread() != holdLockThread) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        isHoldLock = true;
        reentryCount++;
        holdLockThread = Thread.currentThread();
    }

    @Override
    public synchronized void unlock() {
        if (isHoldLock && Thread.currentThread() == holdLockThread) {
            notify();
            isHoldLock = false;
            reentryCount--;
        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
