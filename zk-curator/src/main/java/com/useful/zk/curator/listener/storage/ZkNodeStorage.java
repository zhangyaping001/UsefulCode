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

package com.useful.zk.curator.listener.storage;

import com.useful.zk.curator.base.CoordinatorRegistryCenter;
import com.useful.zk.curator.exception.RegExceptionHandler;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.transaction.CuratorTransactionFinal;
import org.apache.curator.framework.recipes.cache.TreeCache;
import org.apache.curator.framework.recipes.cache.TreeCacheListener;
import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.framework.state.ConnectionStateListener;

import java.util.List;

/**
 * 节点数据访问类.
 *
 * <p>
 * 节点是在普通的节点前加上名称的前缀.
 * </p>
 *
 */
public final class ZkNodeStorage {

    private final CoordinatorRegistryCenter regCenter;

    private final String rootNode;

    public ZkNodeStorage(final CoordinatorRegistryCenter regCenter, final String rootNode) {
        this.regCenter = regCenter;
        this.rootNode = rootNode;
    }

    /**
     * 判断节点是否存在.
     *
     * @param node 节点名称
     * @return 节点是否存在
     */
    public boolean isNodeExisted(final String node) {
        return regCenter.isExisted(getFullPath(node));
    }

    /**
     * 获取节点数据.
     *
     * @param node 节点名称
     * @return 节点数据值
     */
    public String getNodeData(final String node) {
        return regCenter.get(getFullPath(node));
    }

    /**
     * 直接从注册中心而非本地缓存获取节点数据.
     *
     * @param node 节点名称
     * @return 节点数据值
     */
    public String getNodeDataDirectly(final String node) {
        return regCenter.getDirectly(getFullPath(node));
    }

    /**
     * 获取节点子节点名称列表.
     *
     * @param node 节点名称
     * @return 节点子节点名称列表
     */
    public List<String> getNodeChildrenKeys(final String node) {
        return regCenter.getChildrenKeys(getFullPath(node));
    }

    /**
     * 如果存在则创建节点.
     *
     * <p>如果根节点不存在表示已经停止, 不再继续创建节点.</p>
     *
     * @param node 节点名称
     */
    public void createNodeIfNeeded(final String node) {
        if (isRootNodeExisted() && !isNodeExisted(node)) {
            regCenter.persist(getFullPath(node), "");
        }
    }

    private boolean isRootNodeExisted() {
        return regCenter.isExisted("/" + rootNode);
    }

    /**
     * 删除节点.
     *
     * @param node 节点名称
     */
    public void removeNodeIfExisted(final String node) {
        if (isNodeExisted(node)) {
            regCenter.remove(getFullPath(node));
        }
    }

    /**
     * 填充节点数据.
     *
     * @param node 节点名称
     * @param value 节点数据值
     */
    public void fillNode(final String node, final Object value) {
        regCenter.persist(getFullPath(node), value.toString());
    }

    /**
     * 填充临时节点数据.
     *
     * @param node 节点名称
     * @param value 节点数据值
     */
    public void fillEphemeralNode(final String node, final Object value) {
        regCenter.persistEphemeral(getFullPath(node), value.toString());
    }

    /**
     * 更新节点数据.
     *
     * @param node 节点名称
     * @param value 节点数据值
     */
    public void updateNode(final String node, final Object value) {
        regCenter.update(getFullPath(node), value.toString());
    }

    /**
     * 替换节点数据.
     *
     * @param node 节点名称
     * @param value 待替换的数据
     */
    public void replaceNode(final String node, final Object value) {
        regCenter.persist(getFullPath(node), value.toString());
    }

    /**
     * 在事务中执行操作.
     *
     * @param callback 执行操作的回调
     */
    public void executeInTransaction(final TransactionExecutionCallback callback) {
        try {
            CuratorTransactionFinal curatorTransactionFinal = getClient().inTransaction().check().forPath("/").and();
            callback.execute(curatorTransactionFinal);
            curatorTransactionFinal.commit();
        //CHECKSTYLE:OFF
        } catch (final Exception ex) {
        //CHECKSTYLE:ON
            RegExceptionHandler.handleException(ex);
        }
    }

    /**
     * 在主节点执行操作.
     *
     * @param latchNode 分布式锁使用的节点名称
     * @param callback 执行操作的回调
     */
    public void executeInLeader(final String latchNode, final LeaderExecutionCallback callback) {
        try (LeaderLatch latch = new LeaderLatch(getClient(), getFullPath(latchNode))) {
            latch.start();
            latch.await();
            callback.execute();
        //CHECKSTYLE:OFF
        } catch (final Exception ex) {
        //CHECKSTYLE:ON
            handleException(ex);
        }
    }

    private void handleException(final Exception ex) {
        if (ex instanceof InterruptedException) {
            Thread.currentThread().interrupt();
        } else {

        }
    }

    /**
     * 注册连接状态监听器.
     *
     * @param listener 连接状态监听器
     */
    public void addConnectionStateListener(final ConnectionStateListener listener) {
        getClient().getConnectionStateListenable().addListener(listener);
    }

    private CuratorFramework getClient() {
        return (CuratorFramework) regCenter.getRawClient();
    }

    /**
     * 注册数据监听器.
     *
     * @param listener 数据监听器
     */
    public void addDataListener(final TreeCacheListener listener) {
        TreeCache cache = (TreeCache) regCenter.getRawCache("/" + rootNode);
        cache.getListenable().addListener(listener);
    }

    /**
     * 获取注册中心当前时间.
     *
     * @return 注册中心当前时间
     */
    public long getRegistryCenterTime() {
        return regCenter.getRegistryCenterTime(getFullPath("systemTime/current"));
    }


    public String getFullPath(final String node) {
        return String.format("/%s/%s", rootNode, node);
    }
}
