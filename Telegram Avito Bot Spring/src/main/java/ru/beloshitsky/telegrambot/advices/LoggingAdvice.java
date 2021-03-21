package ru.beloshitsky.telegrambot.advices;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Aspect
@Component
public class LoggingAdvice {
  Logger log;

  @Pointcut("execution(* ru.beloshitsky.telegrambot.services.BotService.processUpdate(..))")
  public void botServiceProcessUpdateMethod() {}

  @Pointcut("execution(* ru.beloshitsky.telegrambot.parsers.InputParser.getParsedInput(..))")
  public void inputParserGetParsedInputMethod() {}

  @Pointcut("execution(* ru.beloshitsky.telegrambot.services.AvgPriceCalculator.getAvgPrice(..))")
  public void avgPriceCalculatorGetAvgPriceMethod() {}

  @Pointcut(
      "botServiceProcessUpdateMethod() "
          + "|| inputParserGetParsedInputMethod() "
          + "|| avgPriceCalculatorGetAvgPriceMethod() ")
  public void argsAndRetvalPointcuts() {}

  @Pointcut("execution(* ru.beloshitsky.telegrambot.messages.*.generateMessage(..))")
  public void messageGenerateMessageMethods() {}

  @Pointcut("execution(* ru.beloshitsky.telegrambot.parsers.AvitoHTMLParser.*(..))")
  public void avitoHTMLParserMethods() {}

  @Pointcut("messageGenerateMessageMethods() || avitoHTMLParserMethods()")
  public void argsPointcuts() {}

  @Around("argsAndRetvalPointcuts()")
  public Object logMethodArgsAndRetval(ProceedingJoinPoint pjp) throws Throwable {
    String className = pjp.getThis().getClass().getSimpleName();
    String methodName = pjp.getSignature().getName();
    String args = Arrays.toString(pjp.getArgs());
    log.info("{}.{} ARGS: {}", className, methodName, args);
    Object retval = pjp.proceed();
    log.info("{}.{} RETVAL: {} ", className, methodName, retval);
    return retval;
  }

  @Before("argsPointcuts()")
  public void logMethodArgs(JoinPoint jp) throws Throwable {
    String className = jp.getThis().getClass().getSimpleName();
    String methodName = jp.getSignature().getName();
    String args = Arrays.toString(jp.getArgs());
    log.info("{}.{} ARGS: {}", className, methodName, args);
  }
}
