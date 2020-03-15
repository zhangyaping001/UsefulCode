package com.concurrency.base;

import org.apache.commons.lang.math.RandomUtils;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zhangyaping on 20/3/15.
 */
public class BufferUseCondition {

    private Lock lock = new ReentrantLock();

    Condition bufferFull = lock.newCondition();
    Condition bufferEmpty = lock.newCondition();

    Object[] buffer = new Object[5];

    int count = 0;
    int index = 0;

    public void put(Object o) throws InterruptedException {
        lock.lock();
        try {
            while (count == buffer.length) {
                System.out.println("full await");
                bufferFull.await();
            }
            buffer[index] = o;
            index++;
            count++;

            bufferEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public Object get() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0) {
                System.out.println("empty await");
                bufferEmpty.await();
            }
            index--;
            Object o = buffer[index];
            count--;
            bufferFull.signal();
            return o;
        } finally {
            lock.unlock();
        }

    }

    public static void main(String[] args) throws InterruptedException {
        BufferUseCondition bufferUseCondition = new BufferUseCondition();

        new Thread(() -> {
            try {
                while (true) {
                    bufferUseCondition.put(new Random().nextInt());
                    Thread.sleep(RandomUtils.nextInt(100) * 2);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }).start();

        new Thread(() -> {
            try {
                while (true) {
                    System.out.println(bufferUseCondition.get());
                    Thread.sleep(RandomUtils.nextInt(100) * 2);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }).start();


    }
}
