package com.concurrency.lock.first;

import java.util.concurrent.locks.Lock;

/**
 * Created by zhangyaping on 19/3/3.
 */
public class MyLockTest {

    private static Lock lock = new MyLock();

    public static void methodA() {
        lock.lock();
        System.out.println("Method A invoke...");

        methodB();

        lock.unlock();
    }

    public static void methodB() {
        lock.lock();
        System.out.println("Method B invoke...");
        lock.unlock();
    }

    public static void main(String[] args) throws InterruptedException {
        methodA();


    }
}
