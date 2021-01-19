package com.kruehl.aopdemo.dao;

import org.springframework.stereotype.Component;

@Component
public class MembershipDAO {

    public void addAccount(){

        System.out.println(getClass() + ": DOING MY DB WORK: ADDING ACCOUNT");
    }

    public void goToSleep(){
        System.out.println(getClass() + ": I*m going to sleep now");
    }
}
