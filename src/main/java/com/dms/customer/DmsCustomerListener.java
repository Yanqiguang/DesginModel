package com.dms.customer;

import com.alipay.common.event.UniformEvent;
import com.alipay.common.event.UniformEventContext;
import com.alipay.common.event.UniformEventMessageListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @program: DesignModel
 * @description:
 * @author: lester.yan
 * @create: 2019-04-10 10:18
 **/
@Service("dmsCustomerListener")
public class DmsCustomerListener implements UniformEventMessageListener {

    private static final Logger logger = LoggerFactory.getLogger(DmsCustomerListener.class);

    @Override
    public void onUniformEvent(UniformEvent uniformEvent, UniformEventContext uniformEventContext) throws Exception {
        final String topic = uniformEvent.getTopic();
        final String eventcode = uniformEvent.getEventCode();
        final String id = uniformEvent.getId();
        final Object businessObject = uniformEvent.getEventPayload();
        logger.info("Receive a message, TOPIC [{}] EVENTCODE [{}] id [{}] payload [{}]", new Object[]{topic,
                eventcode, id, businessObject});
        try {
            boolean processSuccess = processMessage(businessObject);
        } catch (Exception e) {
            logger.error("Process a message, failure. TOPIC [{}] EVENTCODE [{}] id [{}] error {}", new Object[]{
                    topic, eventcode, id, e.getMessage()}, e);
            /* When any exception is thrown, the message is re-delivered later. */
            throw e;
        }

    }

    private boolean processMessage(Object businessObject) {
        return true;
    }
}
