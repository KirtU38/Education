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
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Aspect
@Component
public class LoggingAdvice {
  Logger log;

  @Pointcut("execution(* ru.beloshitsky.telegrambot.controller.Controller.*(..))")
  public void controllerPointcut() {}

  @Pointcut("execution(* ru.beloshitsky.telegrambot.services.BotService.processUpdate(..))")
  public void botServicePointcut() {}

  @Pointcut("execution(* ru.beloshitsky.telegrambot.messages.*.getMessage(..))")
  public void messagesPointcut() {}

  @Around("controllerPointcut() || botServicePointcut()")
  public SendMessage logSendMessageReturnSendMessage(ProceedingJoinPoint proceedingJP)
      throws Throwable {

    String className = proceedingJP.getThis().getClass().getSimpleName();
    String methodName = proceedingJP.getSignature().getName();
    Update update = (Update) proceedingJP.getArgs()[0];
    String text = update.getMessage().getText();
    Long chatId = update.getMessage().getChatId();
    log.info("{}.{} ARGS: text: \"{}\", chatId: \"{}\"", className, methodName, text, chatId);
    SendMessage returnMessage = (SendMessage) proceedingJP.proceed();
    String returnText = returnMessage.getText();
    String returnChatId = returnMessage.getChatId();
    log.info(
        "{}.{} RETURN: text: \"{}\", chatId: \"{}\"",
        className,
        methodName,
        returnText,
        returnChatId);
    return returnMessage;
  }

  @Around("messagesPointcut()")
  public SendMessage logTextAndChatIdReturnSendMessage(ProceedingJoinPoint proceedingJP)
      throws Throwable {

    String className = proceedingJP.getThis().getClass().getSimpleName();
    String methodName = proceedingJP.getSignature().getName();
    Object[] args = proceedingJP.getArgs();
    Object text = args[0];
    Object chatId = args[1];
    log.info("{}.{} ARGS: text: \"{}\", chatId: \"{}\"", className, methodName, text, chatId);
    SendMessage returnMessage = (SendMessage) proceedingJP.proceed();
    String returnText = returnMessage.getText();
    String returnChatId = returnMessage.getChatId();
    log.info(
        "{}.{} RETURN: text: \"{}\", chatId: \"{}\"",
        className,
        methodName,
        returnText,
        returnChatId);
    return returnMessage;
  }

  @Before("execution(* ru.beloshitsky.telegrambot.parsers.AvitoHTMLParser.*(..))")
  public void logAvitoHTMLParserGetHTML(JoinPoint jp) throws Throwable {

      String className = jp.getThis().getClass().getSimpleName();
      String methodName = jp.getSignature().getName();
      Object[] args = jp.getArgs();
      Object URL = args[0];
      log.info("{}.{} ARGS: URL: \"{}\"", className, methodName, URL);
    }
}
