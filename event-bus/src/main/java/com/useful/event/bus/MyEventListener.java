package com.useful.event.bus;

public final class MyEventListener implements EventListener {


    @Override
    public void listen(Event event) {

        System.out.println("listen ... " + event.getEventName());
    }

}
