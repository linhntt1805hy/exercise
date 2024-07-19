package org.example;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
public class LoggerAspectJ {
    @Before("execution(* org.example.repository.impl.*.*(..))")
    public void logBeforeCase(JoinPoint joinPoint) {
        System.out.println("Log : " + joinPoint.getSignature().getName() + " at " + LocalDateTime.now());
    }
}
