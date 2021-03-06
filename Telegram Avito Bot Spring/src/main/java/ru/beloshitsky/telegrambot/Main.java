package ru.beloshitsky.telegrambot;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.beloshitsky.telegrambot.configuration.SpringConfig;

public class Main {

  public static void main(String[] args) {
    AnnotationConfigApplicationContext applicationContext =
        new AnnotationConfigApplicationContext(SpringConfig.class);
  }
}
