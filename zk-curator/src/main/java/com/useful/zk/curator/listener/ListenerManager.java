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
import com.useful.zk.curator.listener.election.ElectionListenerManager;
import com.useful.zk.curator.listener.storage.JobNodeStorage;

import java.util.List;

/**
 * 作业注册中心的监听器管理者.
 *
 * @author zhangliang
 */
public final class ListenerManager {

    private final JobNodeStorage jobNodeStorage;

    private final ElectionListenerManager electionListenerManager;


    private final RegistryCenterConnectionStateListener regCenterConnectionStateListener;

    public ListenerManager(final CoordinatorRegistryCenter regCenter, final String jobName/*, final List<ElasticJobListener> elasticJobListeners*/) {
        jobNodeStorage = new JobNodeStorage(regCenter, jobName);
        electionListenerManager = new ElectionListenerManager(regCenter, jobName);
        regCenterConnectionStateListener = new RegistryCenterConnectionStateListener(regCenter, jobName);
    }

    /**
     * 开启所有监听器.
     */
    public void startAllListeners() {
        electionListenerManager.start();
        jobNodeStorage.addConnectionStateListener(regCenterConnectionStateListener);
    }
}
