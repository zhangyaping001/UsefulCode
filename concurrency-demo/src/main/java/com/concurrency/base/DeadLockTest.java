package com.concurrency.base;

/**
 * Created by zhangyaping on 20/3/14.
 */
public class DeadLockTest {

    private Object o1 = new Object();

    private Object o2 = new Object();

    public void method1() throws InterruptedException {
        synchronized (o1) {
            Thread.sleep(1000);
            synchronized (o2) {

            }
            System.out.println("method1 done...");
        }
    }

    public void method2() throws InterruptedException {
        synchronized (o2) {
            Thread.sleep(1000);
            synchronized (o1) {

            }
            System.out.println("method2 done...");
        }
    }

    public static void main(String[] args) {

        DeadLockTest test = new DeadLockTest();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    test.method1();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    test.method2();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
