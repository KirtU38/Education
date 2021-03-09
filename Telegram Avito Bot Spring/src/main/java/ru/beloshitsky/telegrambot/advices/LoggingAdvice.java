package ru.beloshitsky.telegrambot.advices;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
@Aspect
@Component
public class LoggingAdvice {

  @Pointcut(value = "execution(* ru.beloshitsky.telegrambot.controller.Controller.*(..))")
  public void controllerPointcut() {}

  @Pointcut("execution(* ru.beloshitsky.telegrambot.services.BotService.processUpdate(..))")
  public void botServicePointcut() {}

  @Pointcut("execution(* ru.beloshitsky.telegrambot.messages.*.getMessage(..))")
  public void messagesPointcut() {}

  @Around("controllerPointcut() || botServicePointcut()")
  public SendMessage logSendMessageReturnSendMessage(ProceedingJoinPoint proceedingJoinPoint)
      throws Throwable {

    String className =
        proceedingJoinPoint.getThis().getClass().getSimpleName().replaceAll("(^\\w+).*", "$1");
    String methodName = proceedingJoinPoint.getSignature().getName();
    Update update = (Update) proceedingJoinPoint.getArgs()[0];
    String text = update.getMessage().getText();
    Long chatId = update.getMessage().getChatId();
    log.info("{}.{} ARGS: text: \"{}\", chatId: \"{}\"", className, methodName, text, chatId);
    SendMessage returnMessage = (SendMessage) proceedingJoinPoint.proceed();
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
  public SendMessage logTextAndChatIdReturnSendMessage(ProceedingJoinPoint proceedingJoinPoint)
      throws Throwable {

    String className =
        proceedingJoinPoint.getThis().getClass().getSimpleName().replaceAll("(^\\w+).*", "$1");
    String methodName = proceedingJoinPoint.getSignature().getName();
    Object[] args = proceedingJoinPoint.getArgs();
    Object text = args[0];
    Object chatId = args[1];
    log.info("{}.{} ARGS: text: \"{}\", chatId: \"{}\"", className, methodName, text, chatId);
    SendMessage returnMessage = (SendMessage) proceedingJoinPoint.proceed();
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
}
