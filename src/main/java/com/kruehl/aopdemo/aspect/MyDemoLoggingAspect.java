package com.kruehl.aopdemo.aspect;

import com.kruehl.aopdemo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.logging.Logger;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {

    private Logger myLogger = Logger.getLogger(getClass().getName());

    @Around("execution(* com.kruehl.aopdemo.service.*.getFortune(..))")
    public Object aroundGetFortune(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{

        // print out method we are advising on
        String method = proceedingJoinPoint
                .getSignature()
                .toShortString();
        myLogger.info("\n=====>> Executing @After(Finally) on method: "
                + method);
        // get the begin timestamp
        long begin = System.currentTimeMillis();

        // now, lets execute the method
        Object result = proceedingJoinPoint.proceed();

        // get end timestamp
        long end = System.currentTimeMillis();

        // compute duration and display it
        long duration = end - begin;
        myLogger.info("\n=====> Duration: " + duration / 1000.0 + " seconds");

        return result;
    }

    @After("execution(* com.kruehl.aopdemo.dao.AccountDAO.findAccounts(..))")
    public void afetrFInallyFindAccountsAdvice(JoinPoint joinPoint) {

        // print out which method we are advising on
        String method = joinPoint
                .getSignature()
                .toShortString();
        myLogger.info("\n=====>> Executing @After(Finally) on method: "
                + method);
    }


    @AfterThrowing(pointcut = "execution(* com.kruehl.aopdemo.dao.AccountDAO.findAccounts(..))",
            throwing = "exc")
    public void afterThrowingFindAccountAdvice(JoinPoint joinPoint,
                                               Throwable exc) {
        // print out which method we are advising on
        String method = joinPoint
                .getSignature()
                .toShortString();
        myLogger.info("\n=====>> Executing @AfterThrowing on method: " + method);
        // log the exception
        myLogger.info("\n=====>> The exception is: " + exc);
    }


    // add a new advice for @AfterReturning on the findAccounts method

    @AfterReturning(pointcut = "execution(* com.kruehl.aopdemo.dao.AccountDAO.findAccounts(..))",
            returning = "result")
    public void afterReturningFindAccountsAdvice(JoinPoint joinPoint,
                                                 List<Account> result) {
        String method = joinPoint
                .getSignature()
                .toShortString();
        myLogger.info("\n=====>> Executing @AfterReturning on method: " + method);

        // print out the results of the method call
        myLogger.info("\n=====>> result is: " + result);

        // post processing of the data

        // convert the account names to uppercase
        convertAccountNamesToUpperCase(result);

        myLogger.info("\n====>> result is: " + result);
    }

    private void convertAccountNamesToUpperCase(List<Account> result) {
        // loop through accounts
        for (Account tempAccount : result) {
            // get uppercase version of name
            String theUpperName = tempAccount
                    .getName()
                    .toUpperCase();
            // update the name on the account
            tempAccount.setName(theUpperName);
        }


    }


    // @Before advice
    @Before("com.kruehl.aopdemo.aspect.AopExpressions.forDaoPackageNoGetterNoSetter()")
    public void beforeAddAccountAdvice(JoinPoint joinPoint) {
        myLogger.info("\n =====>> Executing @Before advice on method");

        // display the method signature
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        myLogger.info("Method: " + methodSignature);

        // display method arguments

        // get args
        Object[] args = joinPoint.getArgs();

        // loop thru
        for (Object tempArg : args) {
            myLogger.info(tempArg.toString());

            if (tempArg instanceof Account) {
                // downcast and print Account specific stuff
                Account account = (Account) tempArg;

                myLogger.info("account name: " + account.getName());
                myLogger.info("account level: " + account.getLevel());
            }
        }

    }
}
