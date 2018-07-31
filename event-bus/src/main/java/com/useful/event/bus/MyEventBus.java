/*
 * Copyright 1999-2015 dangdang.com.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * </p>
 */

package com.useful.event.bus;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class MyEventBus {


    private final ExecutorServiceObject executorServiceObject;

    private final EventBus eventBus;

    private boolean isRegistered;

    public MyEventBus() {
        executorServiceObject = new ExecutorServiceObject("job-event", Runtime.getRuntime().availableProcessors() * 2);
        eventBus = new AsyncEventBus(executorServiceObject.createExecutorService());
        register();
    }

    private void register() {
        try {
            eventBus.register(new MyEventListener());
            isRegistered = true;
        } catch (Exception ex) {
            log.error("error is: ", ex);
        }
    }

    /**
     * 发布事件.
     *
     * @param event 作业事件
     */
    public void post(final Event event) {
        eventBus.post(event);
    }
}
