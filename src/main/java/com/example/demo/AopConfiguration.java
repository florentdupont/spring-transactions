package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;

import static org.springframework.transaction.support.TransactionSynchronizationManager.isActualTransactionActive;

@Configuration
@Aspect

public class AopConfiguration {


    @Around("@annotation(io.micrometer.core.annotation.Timed)")
    public Object aroundTimed(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        long startTime = System.currentTimeMillis();

        Object result = joinPoint.proceed(joinPoint.getArgs());

        long timeTaken = System.currentTimeMillis() - startTime;
        Logger log = LoggerFactory.getLogger(method.getDeclaringClass());
        log.info("Timed metrics - {}() : {} ms",  method.getName(), timeTaken);

        return result;
    }

    @Around("@annotation(TrackTransation)")
    public Object aroundTrackTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        Object result = joinPoint.proceed(joinPoint.getArgs());

        Logger log = LoggerFactory.getLogger(method.getDeclaringClass());
        log.info("Transaction active - {}() : {} ms",  method.getName(), isActualTransactionActive());

        return result;
    }

     
//
}