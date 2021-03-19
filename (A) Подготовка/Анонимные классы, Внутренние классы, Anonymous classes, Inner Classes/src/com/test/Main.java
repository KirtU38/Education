package com.test;

import java.util.*;

public class Main {
  public static void main(String[] args) {

    // Можно так, и через Сеттеры задать эти Колеса и Двигатель, но нарушается Инкапсуляция, мы все
    // равно имееи доступ к Обьектам этого Класса, которые должны быть только частью Car
    // Чтобы увидеть работы сделай Внутренние Классы public
    // Car nissan = new Car("Nissan");
    // Car.Wheels wheels = nissan.new Wheels(4, "Mischelin");
    // Car.Engine engine = nissan.new Engine(400, "V8");
    //
    // nissan.setEngine(engine);
    // nissan.setWheels(wheels);
    // nissan.getEngine().printEngine();
    // nissan.getWheels().printWheels();

    // А здесь мы создаем все внутри, смотри второй конструктор, там создается this.new Wheels() и в
    // конструктор Wheels передаются значения из этого Конструктора.
    // И внутренние Классы private
    Car toyota = new Car("Toyota", 4, "Mischelin", 350, "V8");

    toyota.printEngineInfo();
    toyota.printWheelsInfo();
    // Если внутренний Класс private, это означает что доступ к его Переменным и Методам имеет
    // только "Внешний Класс", и можно вызывать Методы и Переменные этих Классов только через
    // Обьекты этих Внутренних Классов, созданные внутри "Внешнего"

    // Короче, создаем Wheels внутри Car прям в Конструкторе, и через Конструктор Car передаем
    // значения в Конструктор Обьекта Wheels, потом в Методе Car printWheelsInfo() вызываем метод
    // printWheels() у уже Созданного через Конструктор Car Обьекта wheels.
    // Вот так получается можно работать с Внутренними private Классами.
  }
}
