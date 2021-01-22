package com.kruehl.aopdemo;

import com.kruehl.aopdemo.dao.AccountDAO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class AfterFinallyDemoApp {

    public static void main(String[] args) {

        // read spring config java class
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(DemoConfig.class);

        // get the account bean from the spring container
        AccountDAO accountDAO = context.getBean("accountDAO", AccountDAO.class);

         // call method to find the accounts


        List<Account> myAccounts = null;

        try{
            // add a boolean flag to simulate exceptions
            boolean tripWire = false;
            accountDAO.findAccounts(tripWire);

        } catch (Exception exc){
            System.out.println("\n\nMainProgram ... caught exception: " + exc);
        }

        // display the accounts
        System.out.println("\n\nMain Program: AfterThrowingDemoApp");
        System.out.println("-------");

        System.out.println(myAccounts);

        System.out.println("\n");

        // close the context
        context.close();
    }
}
