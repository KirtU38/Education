package com.innerStaticClass;

public class Main {
  public static void main(String[] args) {

    // Распечатываем static переменные, printAllSubjects() берет переменные из Внутренних Классов,
    // что невозможно с non-static Внутренними Классами
    Constants.printAllSubjects();
    Constants.Mathematic.printMathConstants();
    Constants.Science.printScienceConstants();

    // Так создаются Обьекты Внутренних static Классов
    Constants.Mathematic math = new Constants.Mathematic("Egor");
    Constants.Mathematic math1 = new Constants.Mathematic("Tasia");
    System.out.println(math.author);
    System.out.println(math1.author);
    Constants.Mathematic.printMathConstants();
  }
}
// Inner static classes
// Это Классы, которые создаются внутри другого Класса, и к ним есть доступ без создания Обьекта.

// У Внутренних static Классов могут быть non-static Переменные и Методы, более того, также мы можем
// создавать Обьекты Внутренних static Классов.

// Тогда static и non-static Контексты как бы разделяются, то есть в Классе Constatnts все static
// Переменные, в обычном значении слова static принадлежат Классу, и у ним есть доступ без создания
// Обьекта, однако если там есть non-static методы и переменные, то мы можем создать Обьект
// Внутреннего static Класса и определить эти переменные, и они будут хранится уже в Обьекте.

// По сути отличие Внутреннего static и non-static Классов:
// 1) non-static Внутренний Класс НЕ МОЖЕТ содержать НИЧЕГО static, он существует ТОЛЬКО в связке с
// "Внешним Обьектом" и создается через него.

// 2) static Внутренний Класс МОЖЕТ содержать всё static, но не обязан, он может также содержать и
// Обычные Методы и Переменные, и мы также можем создать Обьект Внутреннего static Класса.

// 3) Самое главное отличие, что static Класс не сможет пользоваться Обычными Методами и Переменными
// Внешнего Обьекта, тк он больше не создается Внутри него.
