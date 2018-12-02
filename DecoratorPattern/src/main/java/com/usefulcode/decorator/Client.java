package com.usefulcode.decorator;

/**
 * Created by zhangyaping on 18/12/2.
 */
public class Client {

    public static void main(String[] args) {
        Component component = new ConcreteDecorator2(
                new ConcreteDecorator1(
                new ConcreteComponent()
        ));

        component.doSomething();

    }
}
