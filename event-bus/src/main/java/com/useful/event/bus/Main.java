package com.useful.event.bus;

/**
 * Created by zhangyaping on 18/8/1.
 */
public class Main {
    public static void main(String[] args) {
        MyEventBus bus = new MyEventBus();
        bus.post(new Event() {
            @Override
            public String getEventName() {
                return "Fuck";
            }
        });
    }
}
