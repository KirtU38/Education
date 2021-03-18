package com.innerClass;

public class Car {
  private int age;
  private String name;

  public Car(int age, String name) {
    this.age = age;
    this.name = name;
  }

  public void info() {
    System.out.println(name + " " + age + " years old");
  }

  public class Engine {
    private int power;
    private String model;
    // Во внутреннем Классе можно создавать Обьект вложенного класса, и именно сам этот Обьект тоже
    Car carReference = Car.this;
    Car carNew = new Car(25, "Toyota");

    public Engine(int power, String model) {
      this.power = power;
      this.model = model;
    }

    public void move() {
      System.out.printf("Car %s is moving with %s engine with %d horsepower", name, model, power);
    }
  }
}
// Мы внутри Класса Car вложили Класс Engine, и именно внутри Engine вызываем метод move(), который
// использует переменные "Внешнего класса"
