package org.sergei.jmsembedded;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by sergei on 2/29/16.
 */
@Service
public class JmsService {

    @Autowired
    private JmsTemplate errorEventQueueJmsTemplate;

    public void sendErrorEventMessage(final String message) {
        errorEventQueueJmsTemplate.send(session -> {
            System.out.println("Service send message: " + message);
            return session.createTextMessage(message);
        });
    }

    public String receiveErrorEventMessage(Long messageId) {
        return (String) errorEventQueueJmsTemplate.receiveSelectedAndConvert("messageId = " + messageId);
    }

}
