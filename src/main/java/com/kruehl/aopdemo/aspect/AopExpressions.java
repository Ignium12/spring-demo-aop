package com.kruehl.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AopExpressions {

    // this where we add all our related advices for logging
    @Pointcut("execution(* com.kruehl.aopdemo.dao.*.*(..))")
    public void forDaoPackage(){}

    // create a point cut for getter methods
    @Pointcut("execution(* com.kruehl.aopdemo.dao.*.get*(..))")
    public void getter(){}

    // create a pointcut for setter methods
    @Pointcut("execution(* com.kruehl.aopdemo.dao.*.set*(..))")
    public void setter(){}

    // create pointcut: include package .... exclude getter/setter
    @Pointcut("forDaoPackage() && !(getter() || setter())")
    public void forDaoPackageNoGetterNoSetter(){}

}
