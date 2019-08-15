package com.zookeeper.service.impl;

import com.zookeeper.distributedlock.DistributedLockByZK;
import com.zookeeper.distributedlock.LockWatcher;
import com.zookeeper.service.ZKservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: DesignModel
 * @description:
 * @author: lester.yan
 * @create: 2019-02-01 17:27
 **/
@Service
public class ZKserviceImpl implements ZKservice {

    @Override
    public String operateZKlock() {
        DistributedLockByZK.startProcess();
        return "success";
    }
}
