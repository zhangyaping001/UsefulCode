package com.useful.zk.curator.listener;

import com.useful.zk.curator.base.CoordinatorRegistryCenter;
import com.useful.zk.curator.listener.server.ServerService;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;

/**
 * 注册中心连接状态监听器.
 *
 * @author zhangliang
 */
public final class RegistryCenterConnectionStateListener implements ConnectionStateListener {

    private final String jobName;

    private final ServerService serverService;


    public RegistryCenterConnectionStateListener(final CoordinatorRegistryCenter regCenter, final String jobName) {
        this.jobName = jobName;
        serverService = new ServerService(regCenter, jobName);
    }

    @Override
    public void stateChanged(final CuratorFramework client, final ConnectionState newState) {
//        if (JobRegistry.getInstance().isShutdown(jobName)) {
//            return;
//        }
        if (ConnectionState.SUSPENDED == newState || ConnectionState.LOST == newState) {
        } else if (ConnectionState.RECONNECTED == newState) {
//            serverService.persistOnline(serverService.isEnableServer(JobRegistry.getInstance().getJobInstance(jobName).getIp()));
        }
    }
}
