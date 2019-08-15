package com.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

/**
 * @program: DesignModel
 * @description:
 * @author: lester.yan
 * @create: 2019-01-29 15:55
 **/
@Service
public class SpringContextUtils implements ApplicationContextAware, DisposableBean {
    private static final String ACTIVE_PROFILE_LOCAL = "local";
    private static ApplicationContext applicationContext = null;
    private static Logger logger = LoggerFactory.getLogger(SpringContextUtils.class);

    public SpringContextUtils() {
    }

    public static ApplicationContext getApplicationContext() {
        assertContextInjected();
        return applicationContext;
    }


    public static <T> T getBean(Class<T> requiredType) {
        assertContextInjected();
        return applicationContext.getBean(requiredType);
    }

    public static String getEnvActiveProfile() {
        if (applicationContext == null) {
            return "local";
        } else {
            ConfigurableApplicationContext configurableApplicationContext = (ConfigurableApplicationContext)applicationContext;
            String[] activeProfiles = configurableApplicationContext.getEnvironment().getActiveProfiles();
            return null != activeProfiles && activeProfiles.length != 0 ? activeProfiles[0] : "local";
        }
    }

    public static void clearHolder() {
        logger.debug("清除SpringContextUtils中的ApplicationContext:" + applicationContext);
        applicationContext = null;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        logger.debug("注入ApplicationContext到SpringContextUtils:{}", applicationContext);
        if (applicationContext != null) {
            logger.warn("SpringContextUtils中的ApplicationContext被覆盖, 原有ApplicationContext为:" + applicationContext);
        }

        applicationContext = applicationContext;
    }

    @Override
    public void destroy() throws Exception {
        clearHolder();
    }

    private static void assertContextInjected() {
        if (applicationContext == null) {
            throw new RuntimeException("applicaitonContext属性未注入, 请在applicationContext.xml中定义SpringContextUtils,同时set lazy-init is false.");
        }
    }
}