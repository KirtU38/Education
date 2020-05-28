package com.company;

import java.util.ArrayList;

public class TodoListManager {

    private static final int START = 1;
    public static ArrayList<String> list = new ArrayList<>();

    public void printItems() {

        if (list.size() == 0) {
            System.out.println("Лист пустой");
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.printf("%d. %s%n", i + START, list.get(i));
        }
    }

    public void addItem(int index, String item) {

        if (index-START >= 0 && index - START <= list.size()) {                       // Добавление по индексу
            list.add(index - START, item);
            System.out.printf("Добавлена задача - %d. %s%n", index, item);
        } else if (index - START > list.size()) {
            list.add(item);
            System.out.println("Индекс слишком большой, будет добавлена задача на последнее место");
            System.out.printf("Добавлена задача - %d. %s%n", list.size() - 1 + START, item);
        } else if (index - START < 0) {
            list.add(0, item);
            System.out.println("Индекс слишком мал, будет добавлена задача на 1 место");
            System.out.printf("Добавлена задача - %d. %s%n", START, item);
        }
    }

    public void addItem(String item) {

        list.add(item);
        System.out.printf("Добавлена задача - %d. %s%n", list.size() - 1 + START, item);
    }

    public void editItem(int index, String item) {

        if (index - START < 0) {
            System.out.println("Индекс слишком мал, будет отредактирована первая задача");
            System.out.printf("Старое имя - %d. %s%n", START, list.get(0));
            list.set(0, item);
            System.out.printf("Новое имя - %d. %s%n", START, list.get(0));
        } else if (index - START >= list.size()) {
            System.out.println("Индекс слишком большой, будет отредактирована последняя задача");
            System.out.printf("Старое имя - %d. %s%n", list.size() - 1 + START, list.get(list.size() - 1));
            list.set(list.size() - 1, item);
            System.out.printf("Новое имя - %d. %s%n", list.size() - 1 + START, list.get(list.size() - 1));
        } else {
            System.out.printf("Старое имя - %d. %s%n", index, list.get(index - START));
            list.set(index - START, item);
            System.out.printf("Новое имя - %d. %s%n", index, list.get(index - START));
        }
    }

    public void deleteItem(int index) {

        if (index - START < 0) {
            System.out.println("Индекс слишком мал, будет удалена первая задача");
            System.out.printf("Удалена задача - %d. %s%n", START, list.get(0));
            list.remove(0);
        } else if (index - START >= list.size()) {
            System.out.println("Индекс слишком большой, будет удалена последняя задача");
            System.out.printf("Удалена задача - %d. %s%n", list.size() - 1 + START, list.get(list.size() - 1));
            list.remove(list.size() - 1);
        } else {
            System.out.printf("Удалена задача - %d. %s%n", index, list.get(index - START));
            list.remove(index - START);
        }
    }
}










