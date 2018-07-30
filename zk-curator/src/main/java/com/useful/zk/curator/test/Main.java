package com.useful.zk.curator.test;

import com.useful.zk.curator.base.CoordinatorRegistryCenter;
import com.useful.zk.curator.listener.ListenerManager;
import com.useful.zk.curator.listener.storage.JobNodeStorage;
import com.useful.zk.curator.zookeeper.ZookeeperConfiguration;
import com.useful.zk.curator.zookeeper.ZookeeperRegistryCenter;

/**
 * Created by zhangyaping on 18/7/31.
 */
public class Main {

    public static void main(String[] args) {
        CoordinatorRegistryCenter registryCenter = createRegistryCenter();
        registryCenter.addCacheData("/demoSimpleJob");
        ListenerManager manager = new ListenerManager(registryCenter, "demoSimpleJob");

        manager.startAllListeners();

//        JobNodeStorage
    }

    private static CoordinatorRegistryCenter createRegistryCenter() {
        CoordinatorRegistryCenter regCenter = new ZookeeperRegistryCenter(new ZookeeperConfiguration("localhost:2181", "elastic-job-demo"));
        regCenter.init();
        return regCenter;
    }
}
