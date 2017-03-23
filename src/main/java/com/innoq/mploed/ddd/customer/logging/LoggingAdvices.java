package com.innoq.mploed.ddd.customer.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static net.logstash.logback.argument.StructuredArguments.entries;

@Aspect
@Component
public class LoggingAdvices {
    public static Logger LOGGER = LoggerFactory.getLogger(LoggingAdvices.class);

    @Around("com.innoq.mploed.ddd.customer.logging.Pointcuts.webServiceLayer() || com.innoq.mploed.ddd.customer.logging.Pointcuts.persistenceLayer()")
    public Object performanceLogging(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long t1 = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        long t2 = System.currentTimeMillis();
        Map structuredArguments = new HashMap();
        structuredArguments.put("log_type", "performance");
        structuredArguments.put("class", proceedingJoinPoint.getTarget().toString());
        structuredArguments.put("time_in_ms", (t2 - t1));
        LOGGER.info("Performance Log: {}", entries(structuredArguments));
        return result;
    }

    @AfterReturning("com.innoq.mploed.ddd.customer.logging.Pointcuts.eventPublisher())")
    public void successfullyProcessedEvents(JoinPoint joinPoint) throws Throwable {
        Map structuredArguments = new HashMap();
        structuredArguments.put("log_type", "successfully_processed_events");
        structuredArguments.put("event", joinPoint.getArgs()[0].getClass().toString());
        LOGGER.info("Event successfully processed: {}", entries(structuredArguments));
    }

}
