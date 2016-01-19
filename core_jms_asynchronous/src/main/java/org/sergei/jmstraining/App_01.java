package org.sergei.jmstraining;

/**
 * Created by sergei on 12/30/15.
 *
 * To run the program configure and start activemq broker
 * (see activemq_doc.txt), than execute the main method.
 *
 * The program output will be:
 *  INFO | Successfully connected to tcp://localhost:61616
 * Send message: 329709310 : main
 *  INFO | Successfully connected to tcp://localhost:61616
 * Received: Hello world! From: main
 *
 *
 *
 */
public class App_01 {
    public static void main(String[] args) {
        HelloWorldProducer.main(null);
        HelloWorldConsumer.main(null);
    }
}
/*


 */
