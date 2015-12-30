package org.sergei.jmstraining.thread;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by sergei on 12/30/15.
 */
public class HelloWorldConsumerThread implements Runnable, ExceptionListener {
    // URL of the JMS server. DEFAULT_BROKER_URL will just mean
    // that JMS server is on localhost
    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
    // default broker URL is : tcp://localhost:61616"

    public void run() {
        try {
            // create connection factory
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
            // create connection
            Connection connection = connectionFactory.createConnection();
            // start connection
            connection.start();
            // create session
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            // create destination topic or queue
            Destination destination = session.createQueue("HELLOWORLD.TESTQ");
            // create consumer
            MessageConsumer consumer = session.createConsumer(destination);
            // get message
            Message message = consumer.receive(1000);
            // print message
            if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                String text = textMessage.getText();
                System.out.println("Received: " + text);
            } else {
                System.out.println("Received: " + message);
            }

            // clean up
            connection.close();
            session.close();
        } catch (Exception e) {
            System.out.println("Caught: " + e);
            e.printStackTrace();
        }
    }

    public synchronized void onException(JMSException exception) {
        System.out.println("JSM Exception occurred. Shutting down client.");
    }
}
