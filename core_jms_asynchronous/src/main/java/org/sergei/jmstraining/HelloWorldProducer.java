package org.sergei.jmstraining;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by sergei on 12/30/15.
 */
public class HelloWorldProducer {
    // URL of the JMS server. DEFAULT_BROKER_URL will just mean
    // that JMS server is on localhost
    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
    // default broker URL is : tcp://localhost:61616"

    public static void main(String[] args) {
        ActiveMQConnectionFactory connectionFactory;
        Connection connection = null;
        Session session = null;

        try {
            // create connection factory
            connectionFactory = new ActiveMQConnectionFactory(url);
            // create connection
            connection = connectionFactory.createConnection();
            // start connection
            connection.start();
            // create session
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            // create destination topic or queue
            Destination destination = session.createQueue("HELLOWORLD.TESTQ");
            // create message producer
            MessageProducer producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT); // set delivery mode
//            producer.setTimeToLive(5000L);

            String text = "Hello world! From: " + Thread.currentThread().getName();
            TextMessage message = session.createTextMessage(text);

//            producer.send(destination, message);
            producer.send(message);
            System.out.println("Send message: " + message.hashCode() + " : " + Thread.currentThread().getName());

        } catch (JMSException e) {
            e.printStackTrace();

        } finally {
            if (session != null) {
                try {
                    session.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
