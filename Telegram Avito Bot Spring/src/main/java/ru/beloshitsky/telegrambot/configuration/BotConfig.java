package ru.beloshitsky.telegrambot.configuration;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Component
public class BotConfig {

  @Value("${telegrambot.token}")
  String token;

  @Value("${telegrambot.userName}")
  String userName;

  @Value("${telegrambot.priceThreshold}")
  int priceThreshold;

  @Value("${telegrambot.pagesLimit}")
  int pagesLimit;

  @Value("${telegrambot.delayBetweenConnections}")
  int delayBetweenConnections;

  @Value("${telegrambot.citiesFile}")
  String citiesFile;

  @Value("${telegrambot.rootURL}")
  String rootURL;
}
