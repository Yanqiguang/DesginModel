package com.rpc.syn.code;

import java.util.List;

import com.alipay.sofa.rpc.config.ProviderConfig;
import com.alipay.sofa.rpc.config.ServerConfig;
import org.assertj.core.util.Lists;

/**
 * @program: DesignModel
 * @description:
 * @author: lester.yan
 * @create: 2018-12-19 11:33
 **/

public class RPCServer {

    public static void main(String[] args) {
        ServerConfig serverConfig = new ServerConfig();
        serverConfig.setProtocol("bolt").setPort(8889).setDaemon(false);
        /**
         * 设置多个协议
         */
        ServerConfig serverConfigA = new ServerConfig();
        serverConfigA.setProtocol("rest");
        ServerConfig serverConfigB = new ServerConfig();
        serverConfigB.setProtocol("dubbo");
        List<ServerConfig> serverConfigs= Lists.newArrayList();
        serverConfigs.add(serverConfig);
        serverConfigs.add(serverConfigA);
        serverConfigs.add(serverConfigB);

        ProviderConfig<HelloSyncService> providerConfig = new ProviderConfig<>();
        providerConfig.setInterfaceId(HelloSyncService.class.getName());
        providerConfig.setRef(new HelloSyncServiceImpl());
        providerConfig.setServer(serverConfigs);
        providerConfig.export();

    }
}
