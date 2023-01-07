package tn.esprit.spring.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
@Aspect
@Slf4j
public class LoggingAspect {
	@Before("execution(* tn.esprit.spring.services..*(..))")
	public void logMethodEntry(JoinPoint joinPoint) {
	String name = joinPoint.getSignature().getName();
	log.info("Début Exécution" + name );
	}

	@After("execution(* tn.esprit.spring.services.StockService.*(..))")
	public void logMethodExit(JoinPoint joinPoint) {
	String name = joinPoint.getSignature().getName();
	log.info("Out of method " + name );
	}
	
	@AfterReturning("execution(* tn.esprit.spring.services.StockService.*(..))")
	public void logMethodAfterReturning(JoinPoint joinPoint) {
	String name = joinPoint.getSignature().getName();
	log.info("success method " + name) ;
	}
	
	@AfterThrowing("execution(* tn.esprit.spring.services.StockService.*(..))")
	public void logMethodAfterThrowing(JoinPoint joinPoint) {
	String name = joinPoint.getSignature().getName();
	log.info("AfterThrowing " + name );
	}
	
	@Around("execution(* tn.esprit.spring.services.StockService.*(..))")
	public Object profile(ProceedingJoinPoint pjp) throws Throwable {
	long start = System.currentTimeMillis();
	Object obj = pjp.proceed();
	long elapsedTime = System.currentTimeMillis() - start;
	log.info("Method execution time: " + elapsedTime + " milliseconds.");
	return obj;
	}



}
