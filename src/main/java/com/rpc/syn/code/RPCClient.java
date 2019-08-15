package com.rpc.syn.code;

import com.alipay.sofa.rpc.config.ConsumerConfig;

/**
 * @program: DesignModel
 * @description:
 * @author: lester.yan
 * @create: 2018-12-19 11:42
 **/

public class RPCClient {

    public static void main(String[] args) {
        ConsumerConfig<HelloSyncService> consumerConfig = new ConsumerConfig<HelloSyncService>();
        consumerConfig.setInterfaceId(HelloSyncService.class.getName());
        consumerConfig.setProtocol("bolt");
        consumerConfig.setDirectUrl("bolt://127.0.0.1:8889");
        //生成代理类
        HelloSyncService helloSyncService = consumerConfig.refer();
        while (true) {
            System.out.println(helloSyncService.saySync("123124"));
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
