package com.rpc.asyn.future;

import java.util.concurrent.*;

import javax.annotation.Resource;

import com.alipay.sofa.rpc.api.future.SofaResponseFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @program: DesignModel
 * @description:
 * @author: lester.yan
 * @create: 2018-12-21 15:26
 **/

@Component(RPCFutrueClient.BEANNAME)
public class RPCFutrueClient {

    public static final String BEANNAME = "rPCFutrueClient";

    private static final Logger logger = LoggerFactory.getLogger(RPCFutrueClient.class);

    @Autowired
    private RPCFutureService rPCFutureService;


    public void RPCFutureMethod(String string) {
        String result = rPCFutureService.futureServer("asyn rpc");

        while (true) {
            try {
                Future future = SofaResponseFuture.getFuture();
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
            try {
                Thread.sleep(2000);
            } catch (Exception ignore) {
            }

            logger.info(" result :" + result + "|| future :");
        }

    }
}
