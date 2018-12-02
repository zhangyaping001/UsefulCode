package com.useful.zk.curator.api.test.baseopr;

import java.util.List;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.Watcher;

public class CrudExample {

    public static void main(String[] args) {
        String connectionString = "127.0.0.1:2181";
        CuratorFramework client = null;
        try {
            client = CreateClientExample.createSimple(connectionString);
            client.start();
            //创建节点
//            create(client, "/example/a", "aa".getBytes());
            //临时节点
//            createEphemeral(client, "/example/b", "aa".getBytes());
            //临时顺序节点
            createEphemeralSequential(client, "/example/a", "aa".getBytes());

            setDataAsync(client, "/example/a", "dd".getBytes());

//            List<String> list = watchedGetChildren(client, "/example/a");

//            System.out.println(list);

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
//            CloseableUtils.closeQuietly(client);
        }

    }

    public static void create(CuratorFramework client, String path, byte[] payload) throws Exception {
        // this will create the given ZNode with the given data
        client.create().forPath(path, payload);
    }

    public static void createEphemeral(CuratorFramework client, String path, byte[] payload) throws Exception {
        // this will create the given EPHEMERAL ZNode with the given data
        client.create().withMode(CreateMode.EPHEMERAL).forPath(path, payload);
    }

    public static String createEphemeralSequential(CuratorFramework client, String path, byte[] payload) throws Exception {
        // this will create the given EPHEMERAL-SEQUENTIAL ZNode with the given
        // data using Curator protection.
        return client.create().withProtection().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath(path, payload);
    }

    public static void setData(CuratorFramework client, String path, byte[] payload) throws Exception {
        // set data for the given node
        client.setData().forPath(path, payload);
    }

    public static void setDataAsync(CuratorFramework client, String path, byte[] payload) throws Exception {
        // this is one method of getting event/async notifications
        CuratorListener listener = new CuratorListener() {

            @Override
            public void eventReceived(CuratorFramework client, CuratorEvent event) throws Exception {
                // examine event for details

                System.out.println("eventReceived....." + event.getType());
            }
        };
        client.getCuratorListenable().addListener(listener);
        // set data for the given node asynchronously. The completion
        // notification
        // is done via the CuratorListener.
        client.setData().inBackground().forPath(path, payload);
    }

    public static void setDataAsyncWithCallback(CuratorFramework client, BackgroundCallback callback, String path, byte[] payload)
        throws Exception {
        // this is another method of getting notification of an async completion
        client.setData().inBackground(callback).forPath(path, payload);
    }

    public static void delete(CuratorFramework client, String path) throws Exception {
        // delete the given node
        client.delete().forPath(path);
    }

    public static void guaranteedDelete(CuratorFramework client, String path) throws Exception {
        // delete the given node and guarantee that it completes
        client.delete().guaranteed().forPath(path);
    }

    public static List<String> watchedGetChildren(CuratorFramework client, String path) throws Exception {
        /**
         * Get children and set a watcher on the node. The watcher notification
         * will come through the CuratorListener (see setDataAsync() above).
         */
        return client.getChildren().watched().forPath(path);
    }

    public static List<String> watchedGetChildren(CuratorFramework client, String path, Watcher watcher) throws Exception {
        /**
         * Get children and set the given watcher on the node.
         */
        return client.getChildren().usingWatcher(watcher).forPath(path);
    }
}