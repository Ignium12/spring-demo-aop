package com.kruehl.aopdemo.aspect;

import com.kruehl.aopdemo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {

    @AfterThrowing(pointcut = "execution(* com.kruehl.aopdemo.dao.AccountDAO.findAccounts(..))",
    throwing = "exc")
    public void afterThrowingFindAccountAdvice(JoinPoint joinPoint, Throwable exc){
        // print out which method we are advising on
        String method = joinPoint.getSignature().toShortString();
        System.out.println("\n=====>> Executing @AfterThrowing on method: " + method);
        // log the exception
        System.out.println("\n=====>> The exception is: " + exc);
    }


    // add a new advice for @AfterReturning on the findAccounts method

    @AfterReturning(pointcut = "execution(* com.kruehl.aopdemo.dao.AccountDAO.findAccounts(..))",
            returning = "result")
    public void afterReturningFindAccountsAdvice(
            JoinPoint joinPoint, List<Account> result) {
        String method = joinPoint.getSignature().toShortString();
        System.out.println("\n=====>> Executing @AfterReturning on method: " + method);

        // print out the results of the method call
        System.out.println("\n=====>> result is: " + result);

        // post processing of the data

        // convert the account names to uppercase
        convertAccountNamesToUpperCase(result);

        System.out.println("\n====>> result is: " + result);
    }

    private void convertAccountNamesToUpperCase(List<Account> result) {
        // loop through accounts
        for(Account tempAccount : result){
            // get uppercase version of name
            String theUpperName = tempAccount.getName().toUpperCase();
            // update the name on the account
            tempAccount.setName(theUpperName);
        }


    }


    // @Before advice
    @Before("com.kruehl.aopdemo.aspect.AopExpressions.forDaoPackageNoGetterNoSetter()")
    public void beforeAddAccountAdvice(JoinPoint joinPoint) {
        System.out.println("\n =====>> Executing @Before advice on method");

        // display the method signature
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        System.out.println("Method: " + methodSignature);

        // display method arguments

        // get args
        Object[] args = joinPoint.getArgs();

        // loop thru
        for (Object tempArg : args) {
            System.out.println(tempArg);

            if (tempArg instanceof Account) {
                // downcast and print Account specific stuff
                Account account = (Account) tempArg;

                System.out.println("account name: " + account.getName());
                System.out.println("account level: " + account.getLevel());
            }
        }

    }
}
