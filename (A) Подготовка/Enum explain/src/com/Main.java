package com;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        Animal animal = Animal.DOG;

        // Через static метод со switch
        Animal.printAnimal(animal);
        // Не static метод, который выводит перевод
        animal.printTranslation();
        // Получаем перевод через Getter
        System.out.println(animal.getTranslation());
        // Через toString() получаем перевод
        System.out.println(animal);
    }
}
// Перечисления в Enum это не строки, а static final Обьекты Класса(например Обьекты Класса Animal).
// Все поля в Enum - это константы.
// Иерархия:
// Object -> Enum -> Animal


