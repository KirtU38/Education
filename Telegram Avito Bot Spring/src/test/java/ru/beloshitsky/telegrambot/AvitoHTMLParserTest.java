package ru.beloshitsky.telegrambot;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Arrays;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import ru.beloshitsky.telegrambot.configuration.BotConfig;
import ru.beloshitsky.telegrambot.parsers.AvitoHTMLParser;
import ru.beloshitsky.telegrambot.parsers.AvitoTagsParser;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class AvitoHTMLParserTest {
  private AvitoHTMLParser avitoHTMLParser;
  
  BotConfig botConfig;
  AvitoTagsParser avitoTagsParser;

  @Before
  public void init() {
    botConfig = Mockito.mock(BotConfig.class);
    avitoTagsParser = new AvitoTagsParser();
    avitoHTMLParser = new AvitoHTMLParser(botConfig, avitoTagsParser);
  }

  @Test
  public void parseHTMLToListOfPricesTest() throws IOException {
    File file = new File("src/test/resources/AvitoTestPage1.html");
    Document htmlDoc = Jsoup.parse(file, "UTF-8");
    List<Double> result = avitoHTMLParser.parseHTMLToListOfPrices(htmlDoc);
    double[] expected = {
      9790.0, 12000.0, 8500.0, 59000.0, 14000.0, 19000.0, 4900.0, 5100.0, 14000.0, 4900.0, 68000.0,
      2700.0, 10500.0, 36000.0, 58000.0, 59000.0, 38700.0, 24000.0, 36000.0, 5100.0, 13700.0,
      9000.0, 10000.0, 56700.0, 7000.0, 57490.0, 78900.0, 58000.0, 10500.0, 78899.0, 11990.0,
      29000.0, 11000.0, 5500.0, 13500.0, 7000.0, 4000.0, 86899.0, 13890.0, 13500.0, 14000.0,
      13000.0, 10000.0, 10000.0, 3000.0, 15000.0, 15990.0, 8000.0, 9000.0, 1.0, 15000.0, 13500.0,
      12000.0, 9600.0, 10000.0
    };
    List expectedList = Arrays.asList(expected);
    assertThat(result).isEqualTo(expectedList);
  }
}
