package com.concurrency.base;

import sun.awt.windows.ThemeReader;

/**
 * Created by zhangyaping on 20/3/7.
 */
public class WaitAndNotifyDemo {


    public static void main(String[] args) throws InterruptedException {
        Object object = new Object();
        //负责唤醒的线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
                // 后面两个线程都获得了锁然后释放了锁并进行了wait, 此线程竞争到同一锁后进行了唤醒,
                // 使后面两个线程由wait变成blocking,并在重新获得锁后运行后续代码
                synchronized (object) {
                    object.notifyAll();
                }
            }
        }).start();
        //第一个获得锁并使当前线程wait的线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (object) {
                    try {
                        object.wait();
                    } catch (InterruptedException e) {
                    }
                    System.out.println("thread 1 do something");
                }
            }
        }).start();
        //第二个获得锁并使当前线程wait的线程
        synchronized (object) {
            object.wait();
            System.out.println("thread 2 do something...");
        }
    }
}
