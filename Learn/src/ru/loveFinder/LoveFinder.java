package ru.loveFinder;

import ru.math.DataType;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

public class LoveFinder {
  static String ROOT = "/Users/egor/IdeaProjects/IdeaProjects-New/Learn/resources/";
  static String PATH_TO_WRITE = ROOT + "love.txt";
  static String KEY_PHRASE = "iloveyou";
  static long LIMIT = DataType.GIGABYTE.count(1);
  static long BUFFER = DataType.MEGABYTE.count(10);

  public static void main(String[] args) throws IOException {
    char[] allChars = {
      'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p', 'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l',
      'z', 'x', 'c', 'v', 'b', 'n', 'm',
    };
    Random random = new Random();
    FileWriter writer = new FileWriter(PATH_TO_WRITE);
    StringBuilder builder = new StringBuilder();
    
    for (long i = 1; ; i++) {
      // Если больше Гига удаляем файл и стартуем заново
      if (i % LIMIT == 0) {
        System.out.println("LIMIT " + i);
        if (builder.toString().contains(KEY_PHRASE)) {
          System.out.println("FOUND in " + i + "iterations");
          writer.flush();
          writer.close();
          break;
        }
        Files.delete(Paths.get(PATH_TO_WRITE));
        writer.flush();
        writer.close();
        writer = new FileWriter(PATH_TO_WRITE);
        System.out.println("DELETED");
      }

      // Сама логика
      int index = random.nextInt(allChars.length);
      builder.append(allChars[index]);

      // Каждые 10 Мегабайт проверяем есть ли в Билдере фраза, если нет кидаем в файл и очищаем
      if (i % BUFFER == 0) {
        System.out.println(builder.length() + " length " + i);
        writer.write(builder.toString());
        if (builder.toString().contains(KEY_PHRASE)) {
          System.out.println("FOUND in " + i + "iterations");
          writer.flush();
          writer.close();
          break;
        }
        builder.delete(0, builder.length());
      }
    }
  }
}
