package ru;

import ru.math.DataType;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BufferVsNoBuffer {
  static String PATH = "/Users/egor/IdeaProjects/IdeaProjects-New/Learn/resources/chars.txt";
  static long SIZE = DataType.MEGABYTE.count(20);

  public static void main(String[] args) throws IOException {
    File file = new File(PATH);
    String chars = "qwetuyasdjzxcnmvzxnyqr";
    Random random = new Random();
    List<String> list = new ArrayList<>();
    long start;

    // Вообще без буфера, пишем каждый символ: почти бесконечность
    // FileOutputStream writer = new FileOutputStream(file);
    // start = System.currentTimeMillis();
    // for (int i = 0; i < SIZE / 100; i++) {
    //   for (int j = 0; j < 100; j++) {
    //     int randomNumber = random.nextInt(chars.length() - 1);
    //     writer.write(chars.charAt(randomNumber));
    //   }
    //   writer.write('\n');
    // }
    // System.out.println(System.currentTimeMillis() - start + " ms");

    // С Буффером 10 килобайт: 35_687 ms, почти как 800 килобайт
    // FileOutputStream fos = new FileOutputStream(file);
    // OutputStreamWriter osw = new OutputStreamWriter(fos);
    // BufferedWriter writer = new BufferedWriter(osw, 10_000);
    // start = System.currentTimeMillis();
    // for (int i = 0; i < SIZE / 100; i++) {
    //   for (int j = 0; j < 100; j++) {
    //     int randomNumber = random.nextInt(chars.length() - 1);
    //     writer.write(chars.charAt(randomNumber));
    //   }
    //   writer.write('\n');
    // }
    // writer.flush();
    // writer.close();
    // System.out.println(System.currentTimeMillis() - start + " ms");

    // С Буффером 800 килобайт: 35_984 ms
    // FileOutputStream fos = new FileOutputStream(file);
    // OutputStreamWriter osw = new OutputStreamWriter(fos);
    // BufferedWriter writer = new BufferedWriter(osw, 800_000);
    // start = System.currentTimeMillis();
    // for (int i = 0; i < SIZE / 100; i++) {
    //   for (int j = 0; j < 100; j++) {
    //     int randomNumber = random.nextInt(chars.length() - 1);
    //     writer.write(chars.charAt(randomNumber));
    //   }
    //   writer.write('\n');
    // }
    // writer.flush();
    // writer.close();
    // System.out.println(System.currentTimeMillis() - start + " ms");

    // С Буффером 200 мегабайт: 32_621 ms
    // FileOutputStream fos = new FileOutputStream(file);
    // OutputStreamWriter osw = new OutputStreamWriter(fos);
    // BufferedWriter writer = new BufferedWriter(osw, 200_000_000);
    // start = System.currentTimeMillis();
    // for (int i = 0; i < SIZE / 100; i++) {
    //   for (int j = 0; j < 100; j++) {
    //     int randomNumber = random.nextInt(chars.length() - 1);
    //     writer.write(chars.charAt(randomNumber));
    //   }
    //   writer.write('\n');
    // }
    // writer.flush();
    // writer.close();
    // System.out.println(System.currentTimeMillis() - start + " ms");

    // С Буффером 900 мегабайт: 21_498 ms, намного быстрее
    FileOutputStream fos = new FileOutputStream(file);
    OutputStreamWriter osw = new OutputStreamWriter(fos);
    BufferedWriter writer = new BufferedWriter(osw, 900_000_000);
    start = System.currentTimeMillis();
    for (int i = 0; i < SIZE / 100; i++) {
      for (int j = 0; j < 100; j++) {
        int randomNumber = random.nextInt(chars.length() - 1);
        writer.write(chars.charAt(randomNumber));
      }
      writer.write('\n');
    }
    writer.flush();
    writer.close();
    System.out.println(System.currentTimeMillis() - start + " ms");

    // Вывод, чем больше буфер, тем быстрее проихсодит запись.
    // Это равно = чем меньше операций записи, тем быстрее и лучше, но нагрузка на оперативку, тк
    // этот буфер держится именно в ней
  }
}
