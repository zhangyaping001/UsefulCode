package com.useful.zk.curator.listener.connection;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;

/**
 * 注册中心连接状态监听器.
 */
public final class RegistryCenterConnectionStateListener implements ConnectionStateListener {

    @Override
    public void stateChanged(final CuratorFramework client, final ConnectionState newState) {
        if (ConnectionState.SUSPENDED == newState || ConnectionState.LOST == newState) {
            System.out.println("suspended or lost");
        } else if (ConnectionState.RECONNECTED == newState) {
            System.out.println("reconnected");
        }
    }
}
