package com.rpc.asyn.future;

import org.springframework.stereotype.Component;

/**
 * @program: DesignModel
 * @description:
 * @author: lester.yan
 * @create: 2018-12-21 15:23
 **/
@Component
public class RPCFutureServiceImpl implements RPCFutureService {

    @Override
    public String futureServer(String string) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return " rpc future asyn server :" + string;
    }
}
