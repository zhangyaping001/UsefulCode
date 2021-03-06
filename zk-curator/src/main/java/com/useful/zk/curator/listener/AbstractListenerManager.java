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

package com.useful.zk.curator.listener;

import com.useful.zk.curator.base.CoordinatorRegistryCenter;
import com.useful.zk.curator.listener.storage.ZkNodeStorage;
import org.apache.curator.framework.recipes.cache.TreeCacheListener;

/**
 * 注册中心的监听器管理者的抽象类.
 */
public abstract class AbstractListenerManager {

    private final ZkNodeStorage zkNodeStorage;

    protected AbstractListenerManager(final CoordinatorRegistryCenter regCenter, final String rootNode) {
        zkNodeStorage = new ZkNodeStorage(regCenter, rootNode);
    }

    /**
     * 开启监听器.
     */
    public abstract void start();

    protected void addDataListener(final TreeCacheListener listener) {
        zkNodeStorage.addDataListener(listener);
    }
}
