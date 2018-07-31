package com.useful.zk.curator.listener.node;

import com.useful.zk.curator.listener.AbstractListener;
import org.apache.curator.framework.recipes.cache.TreeCacheEvent.Type;

class ZkNodeListener extends AbstractListener {

    @Override
    protected void dataChanged(final String path, final Type eventType, final String data) {

        System.out.println("node data changed...");

    }

}