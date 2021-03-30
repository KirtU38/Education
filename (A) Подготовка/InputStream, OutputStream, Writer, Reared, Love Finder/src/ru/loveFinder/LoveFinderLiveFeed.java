package ru.loveFinder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class LoveFinderLiveFeed {
  public static final String ANSI_YELLOW = "\u001B[33m";
  public static final String ANSI_RESET = "\u001B[0m";
  static String ROOT = "/Users/egor/IdeaProjects/IdeaProjects-New/Learn/resources/";
  static String PATH_TO_WRITE = ROOT + "love.txt";
  static String KEY_PHRASE = "m";
  static long BUFFER = 100_000_000;
  static int CHARS_RATE = 150; // 1 символ в 150 мс

  public static void main(String[] args) throws IOException, InterruptedException {
    char[] allChars = {
      'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p', 'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l',
      'z', 'x', 'c', 'v', 'b', 'n', 'm',
    };
    FileWriter writer = new FileWriter(PATH_TO_WRITE);
    Random random = new Random();
    StringBuilder builder = new StringBuilder();

    int charPointer = 0;
    for (long i = 0;; i++) {
      // Сама логика
      long start = System.currentTimeMillis();
      int index = random.nextInt(allChars.length);
      char ch = allChars[index];
      builder.append(ch);
      
      // Логика отображения в консоль и покраска
      if (i % 30 == 0 && i != 0) {
        System.out.println();
      }
      if (ch == KEY_PHRASE.charAt(charPointer)) {
        System.out.print(ANSI_YELLOW + ch + ANSI_RESET);
        charPointer++;
        if (charPointer == KEY_PHRASE.length()) {
          System.out.print(" <- FOUND!");
          break;
        }
      } else {
        System.out.print(ch);
        charPointer = 0;
      }

      // Каждые 10 Мегабайт проверяем есть ли в Билдере фраза, если нет кидаем в файл и очищаем
      if (i % BUFFER == 0 && i != 0) {
        System.out.println(builder.length() + " length " + i);
        if (builder.toString().contains(KEY_PHRASE)) {
          writer.write(builder.toString());
          writer.flush();
          writer.close();
          // System.out.println("FOUND in " + i + " iterations");
          break;
        }
        builder.delete(0, builder.length());
      }
      long sleepTime = CHARS_RATE - (System.currentTimeMillis() - start);
      Thread.sleep(sleepTime <= 0 ? 0 : sleepTime);
    }
  }
}

// InputStream, OutputStream, Writer, Reared, FileWriter, FileReader, FileOutputStream,
// FileInputStream
