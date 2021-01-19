package com.kruehl.aopdemo.aspect;

import com.kruehl.aopdemo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {

    // @Before advice
    @Before("com.kruehl.aopdemo.aspect.AopExpressions.forDaoPackageNoGetterNoSetter()")
    public void beforeAddAccountAdvice(JoinPoint joinPoint){
        System.out.println("\n =====>> Executing @Before advice on method");

        // display the method signature
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        System.out.println("Method: " + methodSignature);

        // display method arguments

        // get args
        Object[] args = joinPoint.getArgs();

        // loop thru
        for(Object tempArg : args){
            System.out.println(tempArg);

            if(tempArg instanceof Account){
                // downcast and print Account specific stuff
                Account account = (Account) tempArg;

                System.out.println("account name: " + account.getName());
                System.out.println("account level: " + account.getLevel());
            }
        }

    }
}
