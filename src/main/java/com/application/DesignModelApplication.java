package com.application;


import java.io.IOException;

import javax.ws.rs.core.Application;

import com.rpc.syn.annotation.AnnotationClient;
import com.rpc.syn.code.HelloSyncService;
import com.rpc.syn.code.HelloSyncServiceImpl;
import com.rpc.syn.code.Person;
import org.mybatis.spring.annotation.MapperScan;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.retry.annotation.EnableRetry;


/**
 * @Description:
 * @Param:
 * @return:
 * @Author: lester.yan
 * @Date: 2018/12/13
 * <p>
 * 排除自动加载数据源配置
 */

/**
 * 排除自动加载数据源配置
 */

/**
 * classpath : 加载 src-main-java下的配置文件
 * localtions：加载 src-resource 下的配置文件
 */
@ImportResource(locations = {"META-INF/spring/application-sofa-rpc.xml"})
@PropertySource("classpath:config/applications.properties")
//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class,SecurityAutoConfiguration.class})
@SpringBootApplication
@ComponentScan("com")
@MapperScan(value = "com")
@EnableRetry
public class DesignModelApplication extends Application {

    private static final Logger logger = LoggerFactory.getLogger(DesignModelApplication.class);


    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(DesignModelApplication.class);
        ApplicationContext applicationContext = springApplication.run(args);
        Person person = (Person) applicationContext.getBean("person");
        System.out.println(person.getAge() + " --" + person.getName());
        System.out.println(person.getHeight());
        System.out.println(person.getFirstName());
        logger.info("-------------- 配置文件方式调用rpc--------------");
        HelloSyncService helloSyncServiceReference = (HelloSyncService) applicationContext.getBean(
                "helloSyncService");
        logger.info(helloSyncServiceReference.saySync("SOFABOOT xxxx"));
        logger.info("-------------- 注解方式调用rpc--------------");
        AnnotationClient client = (AnnotationClient) applicationContext.getBean("annotationClient");
        logger.info(client.annotationClient(" its a rpc type of annotation"));
        logger.info("-------------- 异步方式调用rpc，并获取结果--------------");
/*        RPCFutrueClient rpcFutrueClient=(RPCFutrueClient)applicationContext.getBean("rPCFutrueClient");
        rpcFutrueClient.RPCFutureMethod("rpc start");*/
    }



    @Bean(destroyMethod = "shutdown")
    public RedissonClient redissonClient() throws IOException {
        return Redisson.create(
                Config.fromYAML(new ClassPathResource("config/yml/redisson/redisson.yml").getInputStream())
        );
    }


}