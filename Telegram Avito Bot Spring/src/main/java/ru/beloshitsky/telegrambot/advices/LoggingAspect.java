package ru.beloshitsky.telegrambot.advices;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Aspect
@Component
public class LoggingAspect {

  Logger log = LoggerFactory.getLogger(LoggingAspect.class.getName());

  @Pointcut("@annotation(ru.beloshitsky.telegrambot.advices.annotations.LogArgsAndRetval)")
  public void argsAndRetvalMethods() {}

  @Pointcut("@annotation(ru.beloshitsky.telegrambot.advices.annotations.LogArgs)")
  public void argsMethods() {}
  
  @Around("argsAndRetvalMethods()")
  public Object logMethodArgsAndRetval(ProceedingJoinPoint pjp) throws Throwable {
    String className = pjp.getThis().getClass().getSimpleName().replaceAll("(\\w+)[$].*", "$1");
    String methodName = pjp.getSignature().getName();
    String args = Arrays.toString(pjp.getArgs());
    log.info("{}.{} ARGS: {}", className, methodName, args);
    Object retval = pjp.proceed();
    log.info("{}.{} RETVAL: {} ", className, methodName, retval);
    return retval;
  }

  @Before("argsMethods()")
  public void logMethodArgs(JoinPoint jp) throws Throwable {
    String className = jp.getThis().getClass().getSimpleName().replaceAll("(\\w+)[$].*", "$1");
    String methodName = jp.getSignature().getName();
    String args = Arrays.toString(jp.getArgs());
    log.info("{}.{} ARGS: {}", className, methodName, args);
  }
  
  @Pointcut("execution(* ru.beloshitsky.telegrambot.*.*.*(..))")
  public void allMethods(){}
  
  // @Around("allMethods()")
  public Object speedBenchmark(ProceedingJoinPoint pjp) throws Throwable {
    String className = pjp.getThis().getClass().getSimpleName().replaceAll("(\\w+)[$].*", "$1");
    String methodName = pjp.getSignature().getName();
    long start = System.currentTimeMillis();
    Object retval = pjp.proceed();
    long time = System.currentTimeMillis()-start;
    log.info("{}.{} SPEED: {} ms", className, methodName, time);
    return retval;
  }
}
