package com.innoq.mploed.ddd.customer.logging;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Pointcuts {
    @Pointcut("execution(* com.innoq.mploed.ddd.customer.repository.*.*(..))")
    public void persistenceLayer(){}

    @Pointcut("execution(* com.innoq.mploed.ddd.customer.domainevents.EventPublisher.*(..))")
    public void eventPublisher(){}

    @Pointcut("execution(* com.innoq.mploed.ddd.customer.webservice.*.*(..))")
    public void webServiceLayer(){}

}
