package com.rpc.syn.code;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @program: DesignModel
 * @description:
 * @author: lester.yan
 * @create: 2018-12-19 11:11
 **/

public class HelloSyncServiceImpl implements HelloSyncService {


    @Override
    public String saySync(String string) {
        return " rpc server say : " + string;
    }
}
