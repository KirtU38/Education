package com.innerLocalClass;

public class Animal {
  private int age = 10;

  public void printBear() {
    // Обьявляем Класс внутри метода
    class Bear {
      int weight;
      String breed;

      public Bear(int weight, String breed) {
        this.weight = weight;
        this.breed = breed;
      }

      void infoBear() {
        System.out.printf("%s bear, weight %d, age %d", breed, weight, age);
      }
    }

    // Внутри метода printBear() дальше продолжаем работать с Локальным Классом
    Bear brownBear = new Bear(280, "Brown");
    brownBear.infoBear();
  }
}
// У Класса Animal внутри метода printBear() мы создаем Класс Bear с методом infoBear(),
// Потом создаем в этом же методе Обьект Bear и вызываем у него метод infoBear().
