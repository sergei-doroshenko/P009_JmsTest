package org.sergei.jmstraining.thread;

/**
 * Created by sergei on 12/30/15.
 */
public class App_02 {
    public static void thread (Runnable runnable, boolean demon) {
        Thread thread = new Thread(runnable);
        thread.setDaemon(demon);
        thread.start();
    }

    public static void main(String[] args) throws InterruptedException {
        thread(new HelloWorldProducerThread(), false);
        thread(new HelloWorldProducerThread(), false);
        thread(new HelloWorldProducerThread(), false);
        thread(new HelloWorldProducerThread(), false);
        Thread.sleep(1000);

        thread(new HelloWorldConsumerThread(), false);
        thread(new HelloWorldConsumerThread(), false);
        thread(new HelloWorldProducerThread(), false);
        thread(new HelloWorldConsumerThread(), false);
        thread(new HelloWorldConsumerThread(), false);
        thread(new HelloWorldProducerThread(), false);
        thread(new HelloWorldConsumerThread(), false);
        Thread.sleep(1000);
    }
}
