package com.useful;

/**
 * Created by zhangyaping on 18/8/11.
 */

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;

/**
 * 懒汉 线程不安全
 */
class Singleton1 {

    private static Singleton1 singleton;

    private Singleton1() {

    }

    public static Singleton1 getInstance() {
        if (singleton == null) {
            singleton = new Singleton1();
        }
        return singleton;
    }
}

/**
 * 懒汉 线程安全 性能差
 */
class Singleton2 {

    private static Singleton2 singleton;

    private Singleton2() {

    }

    public static synchronized Singleton2 getInstance() {
        if (singleton == null) {
            singleton = new Singleton2();
        }
        return singleton;
    }
}

/**
 * 饿汉
 */
class Singleton3 {
    private static Singleton3 instance = new Singleton3();

    private Singleton3() {

    }

    public static Singleton3 getInstance() {
        return instance;
    }
}

class Singleton4 {
    private static Singleton4 instance = null;

    static {
        instance = new Singleton4();
    }

    private Singleton4() {

    }

    public static Singleton4 getInstance() {
        return instance;
    }
}

/**
 * 静态内部类 比前两种延迟加载 在jvm初始化阶段完成
 */
class Singleton5 {
    private static class Singleton5Holder {
        private static final Singleton5 instance = new Singleton5();
    }

    private Singleton5() {

    }

    public static final Singleton5 getInstance() {
        return Singleton5Holder.instance;
    }
}

/**
 * 枚举 既无线程安全问题 反序列化也不会有问题
 */
enum Singleton6 {
    INSTANCE;

    public void hi() {

    }
}

/**
 * 双重检验锁 避免了第二种性能不佳的问题
 */
class Singleton7 {
    private volatile static Singleton7 singleton7;

    private Singleton7() {
    }

    public static Singleton7 getInstance() {
        if (singleton7 == null) {
            synchronized (Singleton7.class) {
                if (singleton7 == null) {
                    singleton7 = new Singleton7();
                }
            }
        }
        return singleton7;
    }
}

