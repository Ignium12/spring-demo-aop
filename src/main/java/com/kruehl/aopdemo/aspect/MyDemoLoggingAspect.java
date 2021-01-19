package com.kruehl.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {

    // this where we add all our related advices for logging
    @Pointcut("execution(* com.kruehl.aopdemo.dao.*.*(..))")
    public void forDaoPackage(){}
    // create a point cut for getter methods
    @Pointcut("execution(* com.kruehl.aopdemo.dao.*.get*(..))")
    private void getter(){}
    // create a pointcut for setter methods
    @Pointcut("execution(* com.kruehl.aopdemo.dao.*.set*(..))")
    private void setter(){}
    // create pointcut: include package .... exclude getter/setter
    @Pointcut("forDaoPackage() && !(getter() || setter())")
    private void forDaoPackageNoGetterNoSetter(){}

    // @Before advice
    @Before("forDaoPackageNoGetterNoSetter()")
    public void beforeAddAccountAdvice(){

        System.out.println("\n =====>> Executing @Before advice on addAccount()");
    }

    @Before("forDaoPackageNoGetterNoSetter()")
    public void performApiAnalytics(){
        System.out.println("\n =====>> Performing API analytics");
    }

}
