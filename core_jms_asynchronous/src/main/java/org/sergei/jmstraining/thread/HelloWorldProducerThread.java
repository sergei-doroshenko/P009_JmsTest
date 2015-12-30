package org.sergei.jmstraining.thread;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by sergei on 12/30/15.
 */
public class HelloWorldProducerThread implements Runnable {
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
            // create message producer
            MessageProducer producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT); // set delivery mode

            String text = "Hello world! From: " + Thread.currentThread().getName();
            TextMessage message = session.createTextMessage(text);

            producer.send(message);
            System.out.println("Send message: " + message.hashCode() + " : " + Thread.currentThread().getName());
            // clean up
            connection.close();
            session.close();
        } catch (Exception e) {
            System.out.println("Caught: " + e);
            e.printStackTrace();
        }
    }
}
