package ru.loveFinder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class LoveFinderNoWriting {
  static String ROOT = "/Users/egor/IdeaProjects/IdeaProjects-New/Learn/resources/";
  static String PATH_TO_WRITE = ROOT + "love.txt";
  static String KEY_PHRASE = "mikasa";
  static long BUFFER = 100_000_000;

  public static void main(String[] args) throws IOException {
    char[] allChars = {
      'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p', 'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l',
      'z', 'x', 'c', 'v', 'b', 'n', 'm',
    };
    FileWriter writer = new FileWriter(PATH_TO_WRITE);
    Random random = new Random();
    StringBuilder builder = new StringBuilder();

    for (long i = 1; ; i++) {
      // Сама логика
      int index = random.nextInt(allChars.length);
      builder.append(allChars[index]);

      // Каждые 10 Мегабайт проверяем есть ли в Билдере фраза, если нет кидаем в файл и очищаем
      if (i % BUFFER == 0) {
        System.out.println(builder.length() + " length " + i);
        if (builder.toString().contains(KEY_PHRASE)) {
          writer.write(builder.toString());
          writer.flush();
          writer.close();
          System.out.println("FOUND in " + i + " iterations");
          break;
        }
        builder.delete(0, builder.length());
      }
    }
  }
}

// InputStream, OutputStream, Writer, Reared, FileWriter, FileReader, FileOutputStream,
// FileInputStream
