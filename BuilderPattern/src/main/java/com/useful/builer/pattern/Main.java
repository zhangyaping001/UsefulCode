package com.useful.builer.pattern;

/**
 * Created by zhangyaping on 18/8/5.
 */
public class Main {

    public static void main(String[] args) {
        Dog dd = Dog.newBuilder("hell").height(12).weight(33).Type("DF").build();
        System.out.println(dd);
    }
}
