package com.kruehl.aopdemo;

import com.kruehl.aopdemo.service.TrafficFortuneService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AroundHandleExceptionDemoApp {

    public static void main(String[] args) {

        // read spring config java class
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(DemoConfig.class);

        // get the account bean from the spring container
        TrafficFortuneService trafficFortuneService = context.getBean("trafficFortuneService", TrafficFortuneService.class);

        System.out.println("\nMain Program: AroundDemoApp");
        System.out.println("Calling getFortune");

        boolean tripwire = true;
        String data = trafficFortuneService.getFortune(tripwire);

        System.out.println("\nMy fortune is: " +data);
        System.out.println("Finished");

        // close the context
        context.close();
    }
}
