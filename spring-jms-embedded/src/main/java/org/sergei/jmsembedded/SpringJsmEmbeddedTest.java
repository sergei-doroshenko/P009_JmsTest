package org.sergei.jmsembedded;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;

/**
 * Created by sergei on 2/29/16.
 */
public class SpringJsmEmbeddedTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
        JmsService jsmService = context.getBean(JmsService.class);

        Scanner in = new Scanner(System.in);
        String message;
        while ( !(message = in.nextLine()).equals("exit") ) {
            jsmService.sendErrorEventMessage(message);
        }

        System.out.println("Closed.");
        System.exit(0);
    }
}
