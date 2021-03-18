package com.innerStaticClass;

import java.awt.*;

public class Constants {
  private static String name = "Constants";
  private int x = 7;

  // Получаем доступ к static переменным static Внутреннего Класса, что не можем сделать с
  // non-static внутренним Классом
  public static void printAllSubjects() {
    System.out.printf("Subjects are: %s, %s %n", Mathematic.nameOfSubject, Science.nameOfSubject);
  }

  // Здесь мы в NON-STATIC Методе получаем доступ к STATIC Переменной Внутреннего STATIC Класса
  public void printStaticInnerClassVariable() {
    System.out.println(Mathematic.pi);
  }

  public static class Mathematic {

    // К non-static переменным доступ не имеют
    // String nameOfSubject = "Math";
    private static String nameOfSubject = "Math";
    private static double plank = 398;
    private static double pi = 3.14;
    // non-static переменная будет ТОЛЬКО У ОБЬЕКТА
    public String author;

    public Mathematic(String author) {
      // У всего non-static есть доступ ко всему static(если модификатор доступа позволяет), потому
      // что это логично. Здесь мы меняем при создании Обьекта static число Pi.
      pi = 1;
      this.author = author;
    }

    // Самое главное отличие, что static Класс не сможет пользоваться Обычными Методами и
    // Переменными Внешнего Обьекта, тк он больше не создается Внутри него
    // public void test() {
    //   System.out.println(x);
    // }

    public static void printMathConstants() {
      System.out.printf("%s %s are %f and %f %n", name, nameOfSubject, pi, plank);
    }
  }

  public static class Science {
    private static String nameOfSubject = "Science";
    private static double neutonMass = 8.98725;
    private static double gravity = 1.5098;

    public static void printScienceConstants() {
      System.out.printf("%s %s are %f and %f %n", name, nameOfSubject, neutonMass, gravity);
    }
  }
}
