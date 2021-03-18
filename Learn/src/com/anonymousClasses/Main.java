package com.anonymousClasses;

public class Main {
  private static String name = "Egor";

  public static synchronized void main(String[] args) throws InterruptedException {
    int age = 24;

    // Анонимный класс реализующий Интерфейс
    Runnable runnable =
        new Runnable() {
          @Override
          public void run() {
            System.out.println(name + " is running, his age is " + age);
          }
        };

    // Анонимный Класс наслеюдующий от обычного Класса
    Human human =
        new Human(10_000) {
          @Override
          public void speak() {
            System.out.println("My name is " + name + ", age is " + age);
          }
        };
  }
}
// Anonymous Classes
// Анонимный Класс это по сути Класс без имени.
// Их можно назвать "Одноразовыми Классами", то есть их удобно создавать если нам нужен "Локальный
// Класс для одноразового использования".
// Он либо наследует от Класса, либо реализует Интерфейс.

// 1) Каждый анонимный Класс является уникальным, то есть если обьявить рядом 2 переменные с
// одинаковой реализацией, то это все равно будут разные Классы.

// 2) У Анонимных Классов нельзя задать Конструктор, однако если мы наследуем от Класса где
// Конструктор с параметрами, то нам нужно будет их указать
