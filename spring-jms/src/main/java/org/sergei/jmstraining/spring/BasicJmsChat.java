package org.sergei.jmstraining.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by sergei on 12/31/15.
 *
 * create executable jar
 * mvn clean package
 *
 * run jar
 * java -jar spring-jms-1.0.jar sssssdf
 *
 */
public class BasicJmsChat implements MessageListener {
    private JmsTemplate jmsTemplate;
    private Topic chatTopic;
    private static String userId;

    public static void main(String[] args) throws JMSException, IOException {
        if (args.length != 1) {
            System.out.println("User name is required.");
        } else {
            userId = args[0];
            ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
            BasicJmsChat basicJMSChat = (BasicJmsChat) context.getBean("basicJMSChat");
            TopicConnectionFactory topicConnectionFactory = (TopicConnectionFactory) basicJMSChat.jmsTemplate.getConnectionFactory();
            TopicConnection topicConnection = topicConnectionFactory.createTopicConnection();
            basicJMSChat.publish(topicConnection, basicJMSChat.chatTopic, userId);
            basicJMSChat.subscribe(topicConnection, basicJMSChat.chatTopic, basicJMSChat);
        }

    }

    void publish(TopicConnection topicConnection, Topic chatTopic, String userId) throws JMSException, IOException {
        TopicSession session = topicConnection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
        TopicPublisher topicPublisher = session.createPublisher(chatTopic);
        topicConnection.start();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String msgToSend = bufferedReader.readLine();
            if (msgToSend.equalsIgnoreCase("exit")) {
                topicConnection.close();
                System.exit(0);
            } else {
                TextMessage textMessage = session.createTextMessage();
                textMessage.setText("[" + userId + ": " + msgToSend + "]");
                topicPublisher.publish(textMessage);
            }
        }
    }

    void subscribe(TopicConnection topicConnection, Topic chatTopic, BasicJmsChat basicJMSChat) throws JMSException {
        TopicSession session = topicConnection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
        TopicSubscriber topicSubscriber = session.createSubscriber(chatTopic);
        topicSubscriber.setMessageListener(basicJMSChat);
    }

    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
            try {
                String text = ((TextMessage) message).getText();
                if (text.startsWith("[" + userId)) {
                    System.out.println(text);

                }
            } catch (JMSException e) {
                e.printStackTrace();
            }
        } else {
            String errorMsg = "Message is not of expected type TextMessage.";
            System.err.println(errorMsg);
            throw new RuntimeException(errorMsg);
        }
    }

    public JmsTemplate getJmsTemplate() {
        return jmsTemplate;
    }

    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public Topic getChatTopic() {
        return chatTopic;
    }

    public void setChatTopic(Topic chatTopic) {
        this.chatTopic = chatTopic;
    }

    public static String getUserId() {
        return userId;
    }

    public static void setUserId(String userId) {
        BasicJmsChat.userId = userId;
    }
}
