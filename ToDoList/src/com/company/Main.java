package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    final static int START = 1; // Точка отсчета заданий в списке
    static String input;
    static ArrayList<String> list = new ArrayList<>();

    static int indexOfEdit;
    static String newName;

    private static int deleteIndex;

    public static void main(String[] args) {

        console();

    }

    private static void console() {
        input = scanner.nextLine().trim();

        if (input.matches("list.*") || input.matches("ls.*")) {
            showList();
        } else if (input.matches("add\\s+-?\\d+.*")) {
            addToIndex();
        } else if (input.matches("add\\s+[^-\\d].*")) {
            addStandard();
        } else if (input.matches("delete.*")) {
            delete();
        } else if (input.matches("edit.*")) {
            edit();
        } else if (input.equals("")) {
            System.out.println("Введите что-нибудь");
            console();
        } else {
            System.out.println("Неизвестная команда");
            console();
        }
    }

    private static void showList() {
        if (list.size() == 0) {
            System.out.println("Лист пустой");
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.printf("%d. %s%n", i + START, list.get(i));
        }
        console();
    }

    private static void addStandard() {
        String toDo = input.replaceAll("add\\s+", "").trim();
        list.add(toDo);
        System.out.printf("Добавлена задача - %d. %s%n", list.lastIndexOf(toDo) + START, toDo);
        console();
    }

    private static void addToIndex() {
        String toDo = input.replaceAll("add\\s+-?\\d+", "").trim();
        int additionIndex = Integer.parseInt(input.replaceAll("add\\s+(-?\\d+).*", "$1")) - START;

        if (additionIndex < 0) {
            list.add(0, toDo);
            System.out.println("Индекс слишком мал, будет добавлена задача на 1 место");
        } else if (additionIndex >= list.size()) {
            list.add(toDo);
            System.out.println("Индекс слишком большой, будет добавлена задача на последнее место");
        } else {
            list.add(additionIndex, toDo);
        }

        System.out.printf("Добавлена задача - %d. %s%n", list.lastIndexOf(toDo) + START, toDo);
        console();
    }

    private static void delete() {
        if (list.size() <= 0) {
            System.out.println("Нельзя ничего удалить из пустого листа");
            console();
        }
        if (input.matches("delete")) {
            System.out.println("Выберите какой элемент удалить");
            console();
        }

        if (input.matches("delete\\s+\\d+")) {
            deleteIndex = Integer.parseInt(input.replaceAll("delete\\s+(-?\\d+).*", "$1")) - START;

            if (deleteIndex < 0) {
                printDeleted(0);
                System.out.println("Индекс слишком мал, будет удалена первая задача");
            } else if (deleteIndex > list.size()) {
                printDeleted(list.size() - 1);
                System.out.println("Индекс слишком большой, будет удалена последняя задача");
            } else {
                printDeleted(deleteIndex);
            }
        } else {
            System.out.println("Команда похожа на delete, но выполнить ничего не сможет");
        }

        console();
    }

    private static void printDeleted(int index) {
        deleteIndex = index;
        System.out.printf("Удалена задача - %d. %s%n", deleteIndex + START, list.get(deleteIndex));
        list.remove(index);
    }

    private static void edit() {
        if (list.size() <= 0) {
            System.out.println("Нельзя ничего редактировать в пустом листе");
            console();
        }
        if (input.matches("edit")) {
            System.out.println("Выберите какой элемент редактировать");
            console();
        }

        if (input.matches("edit\\s+\\d+.*")) {
            indexOfEdit = Integer.parseInt(input.replaceAll("edit\\s+(-?\\d+).*", "$1")) - START;
            newName = input.replaceAll("edit\\s+-?\\d+(.*)", "$1").trim();

            if (indexOfEdit < 0) {
                printEdited(0);
                System.out.println("Индекс слишком мал, будет отредактирована первая задача");
            } else if (indexOfEdit >= list.size()) {
                printEdited(list.size() - 1);
                System.out.println("Индекс слишком мал, будет отредактирована последняя задача");
            } else {
                printEdited(indexOfEdit);
            }

        } else {
            System.out.println("Команда конечно похожа на edit, но выполнить она ничего не может");
        }

        console();
    }

    private static void printEdited(int index) {
        indexOfEdit = index;
        System.out.printf("Старое имя - %d. %s%n", indexOfEdit + START, list.get(indexOfEdit));
        list.set(indexOfEdit, newName);
        System.out.printf("Новое имя - %d. %s%n", indexOfEdit + START, list.get(indexOfEdit));
    }
}

