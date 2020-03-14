package com.concurrency.base;

/**
 * Created by zhangyaping on 20/3/8.
 */
public class WaitNotifyTest {

    private  int counter = 0;

//    public synchronized void add(){
//        this.wait();
//    }
//
//    public synchronized void plus(){
//
//    }

    public  void print() {
        Object o = new Object();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (o) {
                    if (counter == 0) {
                        System.out.print(++counter);
                    } else {
                        try {
                            o.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (o) {
                    if (counter == 1) {
                        System.out.print(--counter);
                        o.notify();
                    }
                }
            }
        }).start();
    }

    public static void main(String[] args) {

        WaitNotifyTest test = new WaitNotifyTest();
        for (int i = 0; i < 50; i++) {
            test.print();
        }
    }
}
