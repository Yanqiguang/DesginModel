package com.dms.publisher;

import com.alipay.common.event.UniformEvent;
import com.alipay.common.event.UniformEventBuilder;
import com.alipay.common.event.UniformEventPublisher;
import com.alipay.common.event.impl.DefaultUniformEventBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: DesignModel
 * @description: DmsPublisher
 * @author: lester.yan
 * @create: 2019-04-10 10:01
 **/
@Service
public class DmsPublisher {

    private static final Logger logger = LoggerFactory.getLogger(DmsPublisher.class);

    private final static String TOPIC = "TP_DEFAULT";

    private final static String EVENTCODE = "EC_DEFAULT";

    @Autowired
    private UniformEventPublisher uniformEventPublisher;

    private UniformEventBuilder uniformEventBuilder = new DefaultUniformEventBuilder();

    public boolean publicUniformEvent(String topic, String eventcode, Object payload) {

        // 创建消息，第一个参数是topic, 第二个参数是eventcode
        final UniformEvent uniformEvent = uniformEventBuilder.buildUniformEvent(topic, eventcode);
        // 设置消息负载,一般为业务对象
        uniformEvent.setEventPayload(payload);
        // 设置发消息失败抛出运行时异常
        uniformEvent.setThrowExceptionOnFailed(true);

        try {
            // 发布消息
            logger.info("uniformEventPublisher: " + uniformEventPublisher);
            uniformEventPublisher.publishUniformEvent(uniformEvent);
            logger.info("[Public an uniformEvent, success] topic {} eventcode {} eventId {}",
                    new Object[] { topic, eventcode, uniformEvent.getId() });
        } catch (Exception e) {
            logger.error("[Public an uniformEvent, failure] topic {} eventcode {} eventId {} e{}",
                    new Object[] { topic, eventcode, uniformEvent.getId(), e });
            return false;
        }
        return true;
    }





}
