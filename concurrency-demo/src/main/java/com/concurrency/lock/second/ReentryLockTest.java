package com.concurrency.lock.second;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zhangyaping on 19/3/3.
 */
public class ReentryLockTest {

    private static ReentrantLock lock = new ReentrantLock();

    public static void methodA() throws InterruptedException {
        lock.lock();
        System.out.println("method a");
        methodB();
        Thread.sleep(5000);
        lock.unlock();
    }

    public static void methodB() {
        lock.lock();
        System.out.println("method b");
        lock.unlock();
    }

    public static void main(String[] args) throws InterruptedException {
        methodA();
    }
}
