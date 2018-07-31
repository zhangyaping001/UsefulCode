package com.useful.zk.curator.test;

import com.useful.zk.curator.base.CoordinatorRegistryCenter;
import com.useful.zk.curator.listener.ListenerManager;
import com.useful.zk.curator.zookeeper.ZookeeperConfiguration;
import com.useful.zk.curator.zookeeper.ZookeeperRegistryCenter;

/**
 * Created by zhangyaping on 18/7/31.
 */
public class Main {

    private static String rootNode = "CommonSimpleJob1OK";

    public static void main(String[] args) throws InterruptedException {

        CoordinatorRegistryCenter registryCenter = createRegistryCenter();

        ListenerManager manager = new ListenerManager(registryCenter, rootNode);

        manager.startAllListeners();

        while (true) {
            Thread.sleep(100000);
        }
    }

    private static CoordinatorRegistryCenter createRegistryCenter() {
        CoordinatorRegistryCenter regCenter = new ZookeeperRegistryCenter(new ZookeeperConfiguration("localhost:2181", "elastic-job-ns2"));
        regCenter.init();
        regCenter.addCacheData("/" + rootNode);
        return regCenter;
    }
}
