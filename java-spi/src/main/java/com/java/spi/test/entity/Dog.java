package com.java.spi.test.entity;

import java.io.Serializable;

/**
 * Created by zhangyaping on 2019/7/9.
 */
public class Dog implements Serializable{

    private String name;

    public Dog(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Dog{" +
            "name='" + name + '\'' +
            '}';
    }
}
