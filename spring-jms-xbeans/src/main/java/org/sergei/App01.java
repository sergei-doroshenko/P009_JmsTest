package org.sergei;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;

/**
 * Created by sergei on 1/20/16.
 */
public class App01 {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        MyBean myBean = context.getBean(MyBean.class);
        System.out.println(myBean.getDescription());
        SimpleBean simpleBean = context.getBean(SimpleBean.class);
    }
}
