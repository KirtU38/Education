package ru.beloshitsky.telegrambot.configuration;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@ConfigurationProperties(prefix = "telegrambot")
@Component
public class BotConfig {
  String token;
  String userName;
  String webHookPath;
  int priceThreshold;
  int pagesLimit;
  int delayBetweenConnections;
  String citiesFile;
  String rootURL;
}
