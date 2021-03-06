package ru.beloshitsky.SpringAOP;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

// Здесь Логгер Advice работающий через @Before, @After и тд

@Component
@Aspect
public class LoggingAdvice {
  Logger log = LoggerFactory.getLogger(Controller.class);

  @Before("execution(* ru.beloshitsky.SpringAOP.Controller.*(..))")
  public void logBefore(JoinPoint joinPoint) {
    log.info("Before " + joinPoint.getSignature().getName());
  }

  @After("execution(* ru.beloshitsky.SpringAOP.Controller.*(..))")
  public void logAfter(JoinPoint joinPoint) {
    log.info("After " + joinPoint.getSignature().getName());
  }

  @Before("execution(* ru.beloshitsky.SpringAOP.Controller.*(..))")
  public void logParams(JoinPoint joinPoint) {
    log.info("Before with Params" + Arrays.toString(joinPoint.getArgs()));
  }

  @AfterReturning(
      pointcut = "execution(* ru.beloshitsky.SpringAOP.Controller.*(..))",
      returning = "result")
  public void logReturn(JoinPoint joinPoint, Object result) {
    log.info("AfterReturning " + joinPoint.getSignature().getName() + "  " + result);
  }
}
// Aspect - тот функционал, который мы хотим реализовать, например "Реализовать логгирование в
// бизнес-layer", то есть "Вынести логгирование из б  изнес-layer`а" (применяется в security,
// transactions и logging).

// Pointcut - задает какие методы мы будем перехватывать, это что-то вроде Регулярного выражения.

// Advice - бизнес-логика, которая выполняется, когда мы достигаем Pointcut`а.

// Join point - метод, которого мы достигли через Pointcut, мы можем получить его имя, аргументы и
// тд

// @Before - значит Advice будет выполняться вначале метода
// @After - в конце метода
// @AfterReturning - после того, как метод вернет значение

// Pointcut выражение
// "execution(* ru.beloshitsky.SpringAOP.Controller.*(..))"

// * - вначале означает фильтрация по сигнатуре метода, может быть типа "public String".

// .* Дальше путь к методу, это может быть весь класс, целый пакет и тд

// (..) - это фильтрация по типу Параметров, может быть (Integer) или (String) и тд
