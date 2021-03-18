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

    // Анонимный Класс наслеюдующий от обычного Класса, как видим у него есть доступ к Переменным
    // Класса, так и к Локальным переменным.
    Human human =
        new Human(10_000) {
          // В анонимном Классе можно создавать Переменные
          int x = 10;

          {
            System.out.println("Anon class block of code");
          }

          @Override
          public void speak() {
            System.out.println(
                "My name is "
                    + name
                    + ", age is "
                    + age
                    + ", salary is "
                    + getSalary()
                    + ", variable"
                    + x);
          }
        };
    human.speak();
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

// 3) Можно создавать внутри Переменные и Блоки кода

// 4) Тк Анонимные Классы не являются static (они в принципе привязаны уже к какому-то Обьекту),
// то у них не может быть ничего static
