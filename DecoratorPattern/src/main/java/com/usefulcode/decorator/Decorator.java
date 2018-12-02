package com.usefulcode.decorator;

/**
 * Created by zhangyaping on 18/12/2.
 */
public class Decorator implements Component {

    private Component component;

    public Decorator(Component component) {
        this.component = component;
    }

    @Override
    public void doSomething() {
        component.doSomething();
    }
}
