package ru.inputStreamAndReader;

import ru.math.DataType;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Читаем из файла каким-то способом все элементы и пишем в другой файл все индексы, на которых
// встречается указанный символ.
public class InputStreamAndReaderAnotherMethod {
  static String ROOT = "/Users/egor/IdeaProjects/IdeaProjects-New/Learn/resources/";
  static String PATH_TO_READ = ROOT + "chars.txt";
  static String PATH_TO_WRITE = ROOT + "result.txt";

  public static void main(String[] args) throws IOException {
    File file = new File(PATH_TO_READ);
    // Текст
    // Лучше всего через static методы Files
    // Читает файл в одну строку
    // String oneString = Files.readString(Paths.get(PATH));
    // System.out.println(oneString.length());
    //
    // // Читает с \n в Лист линий
    // List<String> lines = Files.readAllLines(Paths.get(PATH));
    // System.out.println(lines.size());

    // Через Reader, читает byte, но кастим в char
    FileReader reader = new FileReader(file);
    Map<Integer, String> map = new HashMap<>();
    int ch;
    int index = 0;
    while ((ch = reader.read()) != -1) {
      map.put(index++, String.valueOf((char) ch));
    }
    System.out.println(map.size());
    System.out.println("DONE");

    // Здесь мы пишем в файл все индексы Переносов строки, всё через HashMap, потому что с Листом
    // просто СУПЕР медленно из-а того, что при каждом вызове contains() ему приходилось заново
    // обходить весь Лист.
    // Каждые 100_000 элементов в listOfIndexes происходит Запись и Очистка, иначе Heap кончается.
    long start = System.currentTimeMillis();
    List<String> listOfIndexes = new ArrayList<>();
    boolean isCreated = false;
    for (Integer indx : map.keySet()) {
      String letter = map.get(indx);
      if (letter.equals("\n")) {
        listOfIndexes.add(String.valueOf(indx));

        if (listOfIndexes.size() > 100_000) {
          StandardOpenOption operation;
          if (isCreated) {
            operation = StandardOpenOption.APPEND;
          } else {
            operation = StandardOpenOption.CREATE;
            isCreated = true;
          }
          // Здесь запись
          Files.write(Paths.get(PATH_TO_WRITE), listOfIndexes, operation,
          StandardOpenOption.WRITE);
          listOfIndexes.clear();
        }
      }
    };
    // Он пишет именно ЛИНИЯМИ, то есть также, как читает Files.readAllLines()
    Files.write(
        Paths.get(PATH_TO_WRITE),
        listOfIndexes,
        StandardOpenOption.APPEND,
        StandardOpenOption.WRITE);
    
    System.out.println(System.currentTimeMillis() - start + " ms");
    System.out.println("DONE");
  }
  
  private static void writeToFile(FileWriter fileWriter, List<String> listOfIndexes) {
    listOfIndexes.forEach(e -> {
      try {
        fileWriter.write(e);
      } catch (IOException ioException) {
        ioException.printStackTrace();
      }
    });
  }
}
