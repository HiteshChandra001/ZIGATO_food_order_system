package com.zigato.service;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AOPLoggingAspect {
	private static final Logger logger = LoggerFactory.getLogger(AOPLoggingAspect.class);

	@Pointcut("execution(public * com.zigato.service..*(..))")
	 public void applicationPackagePointcut() {
        // Pointcut for all methods in com.zigato.service package and its sub-packages
    }

//    @Before("applicationPackagePointcut()")
//    public void logBefore() {
//        logger.info("A method is about to be called");
//    }
//
//    @After("applicationPackagePointcut()")
//    public void logAfter() {
//        logger.info("A method has just been called");
//    }
//    

    // After returning advice
    @AfterReturning(pointcut = "applicationPackagePointcut()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        logger.info("Method {} returned: {}", joinPoint.getSignature(), result);
    }
    
    
    @Around("applicationPackagePointcut()")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        Object proceed = joinPoint.proceed();  // Proceed with the method execution

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        logger.info("Method {} executed in {} ms", joinPoint.getSignature(), duration);

        return proceed;
    }

    // After throwing advice
    @AfterThrowing(pointcut = "applicationPackagePointcut()", throwing = "error")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
        logger.error("Method {} threw exception: {}", joinPoint.getSignature(), error.getMessage());
    }
	
}
