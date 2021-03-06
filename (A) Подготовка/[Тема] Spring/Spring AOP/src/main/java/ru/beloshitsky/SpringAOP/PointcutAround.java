package ru.beloshitsky.SpringAOP;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

// Здесь Логгер Advice работающий через заранее заданные @Pointcut и ловящие их методы @Around

@Aspect
@Component
public class PointcutAround {
  Logger log = LoggerFactory.getLogger(Controller.class);

  // @Pointcut(value = "execution(* ru.beloshitsky.SpringAOP.Controller.*(..))")
  // public void controllerPointcut() {}
  //
  // @Around("controllerPointcut()")
  // public void logBefore(ProceedingJoinPoint joinPoint) throws Throwable {
  //   log.info("Before: " + joinPoint.getSignature().getName());
  //   Object returningObject = joinPoint.proceed();
  //   log.info("Returning: " + returningObject);
  // }
}
// @Around = @Before + @AfterReturning
