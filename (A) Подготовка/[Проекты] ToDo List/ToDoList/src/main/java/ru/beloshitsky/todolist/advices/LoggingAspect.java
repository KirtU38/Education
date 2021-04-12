package ru.beloshitsky.todolist.advices;

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
import ru.beloshitsky.todolist.controller.ToDoController;

import java.util.Arrays;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Aspect
@Component
public class LoggingAspect {

  Logger log = LoggerFactory.getLogger(ToDoController.class.getName());

  @Pointcut("@annotation(ru.beloshitsky.todolist.advices.annotations.LogArgs)")
  public void logArgsAnnotation() {}

  @Pointcut("@annotation(ru.beloshitsky.todolist.advices.annotations.LogRetval)")
  public void logRetvalAnnotation() {}

  @Pointcut("@annotation(ru.beloshitsky.todolist.advices.annotations.LogArgsAndRetval)")
  public void logArgsAndRetvalAnnotation() {}

  @Before("logArgsAnnotation()")
  public void logMethodArgs(JoinPoint jp) {
    String methodName = jp.getSignature().getName();
    String args = Arrays.toString(jp.getArgs());
    log.info("{} ARGS: {}", methodName, args);
  }

  @Around("logRetvalAnnotation()")
  public Object logMethodRetval(ProceedingJoinPoint pjp) throws Throwable {
    String methodName = pjp.getSignature().getName();
    Object retval = pjp.proceed();
    log.info("{} RETVAL: {} ", methodName, retval);
    return retval;
  }

  @Around("logArgsAndRetvalAnnotation()")
  public Object logMethodArgsAndRetval(ProceedingJoinPoint pjp) throws Throwable {
    String methodName = pjp.getSignature().getName();
    String args = Arrays.toString(pjp.getArgs());
    log.info("{} ARGS: {}", methodName, args);
    Object retval = pjp.proceed();
    log.info("{} RETVAL: {} ", methodName, retval);
    return retval;
  }
}
