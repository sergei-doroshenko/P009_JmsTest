package org.sergei.jmstraining.spring;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * Created by sergei on 12/30/15.
 */
public class JsmTemplateTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        JmsTemplate jsmTemplate = (JmsTemplate) context.getBean("jmsTemplate");

        jsmTemplate.send("SpringSendTestQueue", new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                TextMessage message = session.createTextMessage();
                message.setText("This is a test message!");
                return message;
            }
        });

        System.out.println("Message send.");
        System.exit(0);
    }
}
