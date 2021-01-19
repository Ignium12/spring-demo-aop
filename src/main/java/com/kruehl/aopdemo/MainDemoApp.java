package com.kruehl.aopdemo;

import com.kruehl.aopdemo.dao.AccountDAO;
import com.kruehl.aopdemo.dao.MembershipDAO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainDemoApp {

    public static void main(String[] args) {

        // read spring config java class
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(DemoConfig.class);

        // get the account bean from the spring container
        AccountDAO accountDAO = context.getBean("accountDAO", AccountDAO.class);

        // get the membership bean from spring container
        MembershipDAO membershipDAO = context.getBean("membershipDAO", MembershipDAO.class);

        // call the membership business method
        membershipDAO.addAccount();
        membershipDAO.goToSleep();

        // call the business method
        Account account = new Account();
        accountDAO.addAccount(account, true);
        accountDAO.doWork();

        // call the countdao getter/setter methods
        accountDAO.setName("foobar");
        accountDAO.setServiceCode("silver");
        accountDAO.getName();
        accountDAO.getServiceCode();


        // close the context
        context.close();
    }
}
