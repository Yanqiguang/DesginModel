package com.zookeeper.distributedlock;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @program: DesignModel
 * @description: zk分布式锁中watch模式
 * @author: lester.yan
 * @create: 2019-02-01 15:10
 **/
public class LockWatcher implements Watcher {

    private static final Logger logger = LoggerFactory.getLogger(LockWatcher.class);

    private ZooKeeper zk = null;
    /**
     * 当前业务线程竞争锁的时候创建的节点路径
     */
    private String selfPath = null;
    /**
     * 当前业务线程竞争锁的时候创建节点的前置节点路径
     */
    private String waitPath = null;
    /**
     * 确保连接zk成功；只有当收到Watcher的监听事件之后，才执行后续的操作，否则请求阻塞在createConnection()创建ZK连接的方法中
     */
    private CountDownLatch connectSuccessLatch = new CountDownLatch(1);
    /**
     * 标识线程是否执行完任务
     */
    private CountDownLatch threadCompleteLatch = null;

    /**
     * ZK的相关配置常量
     **/
    private static final String LOCK_ROOT_PATH = "/myDisLocks";
    private static final String LOCK_SUB_PATH = LOCK_ROOT_PATH + "/thread";


    public LockWatcher(CountDownLatch threadCompleteLatch) {
        this.threadCompleteLatch = threadCompleteLatch;
    }

    @Override
    public  void process(WatchedEvent watchedEvent) {
        if (watchedEvent == null) {
            return;
        }
        //设置通知状态
        Event.KeeperState keeperState = watchedEvent.getState();
        //事件类型
        Event.EventType eventType = watchedEvent.getType();
        //根据状态进行处理
        if (Event.KeeperState.SyncConnected == keeperState) {
            if (Event.EventType.None == eventType) {
                logger.info(Thread.currentThread().getName() + "成功连接上ZK服务器");
                connectSuccessLatch.countDown();
            } else if (Event.EventType.NodeDeleted == eventType && watchedEvent.getPath() == waitPath) {
                logger.info(Thread.currentThread().getName() + "上一节点已经解锁，确认本节点是否为最小节点");
            }
        }
    }

    /**
     * @Description: 创建ZK连接
     * @Param: [connectString, sessionTimeout]
     * @return: void
     * @Author: lester.yan
     * @Date: 2019/2/1
     */
    public void createConnection(String connectString, int sessionTimeout) throws IOException, InterruptedException {
        zk = new ZooKeeper(connectString, sessionTimeout, this);
        //超时阻塞
        connectSuccessLatch.await(1, TimeUnit.SECONDS);
    }


    /**
     * @Description: 创建临时节点
     * @Param: [path, data, needWatch]
     * @return: boolean
     * @Author: lester.yan
     * @Date: 2019/2/1
     */
    public boolean createPersistentPath(String path, String data, boolean needWatch) throws KeeperException,
            InterruptedException {
        if (zk.exists(path, needWatch) == null) {
            String result = zk.create(path, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
                    CreateMode.PERSISTENT_SEQUENTIAL);
            logger.info(Thread.currentThread().getName() + "创建节点成功, path: " + result + ", content: " + data);
        }
        return true;
    }

    /** 
    * @Description: 获取锁
    * @Param: []  
    * @return: void 
    * @Author: lester.yan 
    * @Date: 2019/2/1 
    */ 
    public void getLock() throws Exception {
        selfPath = zk.create(LOCK_SUB_PATH, null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        logger.info(Thread.currentThread().getName() + "创建锁路径:" + selfPath);
        if (checkMyselfPoint()) {
            getLockSuccess();
        }
    }


    /**
    * @Description:  获取锁成功
    * @Param: []
    * @return: void
    * @Author: lester.yan
    * @Date: 2019/2/1
    */
    private void getLockSuccess() throws KeeperException, InterruptedException {
        if(zk.exists(selfPath, false) == null) {
            logger.info(Thread.currentThread().getName() + "本节点已不在了...");
            return;
        }
        logger.info(Thread.currentThread().getName() + "获取锁成功，开始处理业务数据！");
        Thread.sleep(2000);
        logger.info(Thread.currentThread().getName() + "处理业务数据完成，删除本节点：" + selfPath);
        zk.delete(selfPath, -1);
        releaseConnection();
        threadCompleteLatch.countDown();
    }

    /**
     * @Description: 关闭zk链接
     * @Param: []
     * @return: void
     * @Author: lester.yan
     * @Date: 2019/2/1
     */
    private void releaseConnection() {
        if (zk != null) {
            try {
                zk.close();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + "释放ZK连接");
    }

    /**
     * @Description: 检查自己节点是否为最小节点，是否能获取锁
     * @Param: []
     * @return: boolean
     * @Author: lester.yan
     * @Date: 2019/2/1
     */
    public boolean checkMyselfPoint() throws Exception {
        List<String> subNodes = zk.getChildren(LOCK_ROOT_PATH, false);
        Collections.sort(subNodes);
        logger.info(Thread.currentThread().getName() + "创建的临时节点名称:" + selfPath.substring(LOCK_ROOT_PATH.length() + 1));
        int index = subNodes.indexOf(selfPath.substring(LOCK_ROOT_PATH.length() + 1));
        switch (index) {
            case -1: {
                logger.info("节点已经不存在");
                return false;
            }
            case 0: {
                logger.info("在子节点中我，我最小，可以获取锁");
            }
            default: {
                // 获取比当前节点小的前置节点,此处只关注前置节点是否还在存在，避免惊群现象产生
                waitPath = LOCK_ROOT_PATH + "/" + subNodes.get(index - 1);
                logger.info("在我前面的节点是 ：" + waitPath);
                try {
                    zk.getData(waitPath, true, new Stat());
                    return false;
                } catch (Exception e) {
                    if (zk.exists(waitPath, false) == null) {
                        logger.info(Thread.currentThread().getName() + "子节点中，排在我前面的" + waitPath + "已失踪，该我了");
                        return checkMyselfPoint();
                    } else {
                        throw e;
                    }
                }
            }
        }
    }
}
