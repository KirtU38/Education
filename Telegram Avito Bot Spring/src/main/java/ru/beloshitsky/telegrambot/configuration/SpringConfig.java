package ru.beloshitsky.telegrambot.configuration;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.beloshitsky.telegrambot.messages.Message;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@ComponentScan("ru/beloshitsky/telegrambot")
@PropertySource("classpath:application.properties")
@Configuration
public class SpringConfig {

  @Bean("mapOfCities")
  public Map<String, String> mapOfCities(BotConfig botConfig) throws IOException {
    String citiesFile = botConfig.getCitiesFile();
    HashMap<String, String> mapOfCities = new HashMap<>();

    Files.readAllLines(Paths.get(citiesFile))
        .forEach(
            e -> {
              String[] tokens = e.split("=", 2);
              mapOfCities.put(tokens[0], tokens[1]);
            });
    return mapOfCities;
  }

  @Bean("mapOfMessages")
  public Map<String, Message> mapOfMessages(List<Message> listOfMessages) {
    return listOfMessages.stream().collect(Collectors.toMap(Message::getId, Function.identity()));
  }

  @Bean
  public Logger logError() {
    return LoggerFactory.getLogger("errorLogger");
  }
}
