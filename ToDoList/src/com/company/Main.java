package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        while (true) {

            TodoListManager manager = new TodoListManager();
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine().trim();
            String item = "";
            int index = 0;
            boolean actionWithIndex = input.matches("\\w+\\s+-?\\d+\\s?.*");
            boolean hasItem = input.matches("\\w+\\s+-?\\d*\\s*\\w+.*");
            boolean matchesPrint = input.matches("list.*") || input.matches("ls.*");
            boolean matchesAddition = input.matches("add\\s+\\d*[a-z]\\d*.*");
            boolean matchesIndexAddition = input.matches("add\\s+-?\\d+\\s+.*");
            boolean matchesDelete = input.matches("delete\\s+-?\\d+\\s*");
            boolean matchesDeleteNoIndex = input.matches("delete");
            boolean matchesEdit = input.matches("edit\\s+-?\\d+\\s+.*");
            boolean matchesEditNoIndex = input.matches("edit");
            boolean matchesEditNoItem = input.matches("edit\\s+-?\\d+");
            boolean matchesBlank = input.equals("");

            if (hasItem) {
                item = input.replaceAll("\\w+\\s+-?\\d*\\s*(\\w+.*)", "$1").trim();
            }
            if (actionWithIndex) {
                index = Integer.parseInt(input.replaceAll("\\w+\\s+(-?\\d+)\\s?.*", "$1").trim());
            }

            if (matchesPrint) {
                manager.printItems();
            } else if (matchesAddition) {
                manager.addItem(item);
            } else if (matchesIndexAddition) {
                manager.addItem(index, item);
            } else if (matchesDelete) {
                manager.deleteItem(index);
            } else if (matchesEdit) {
                manager.editItem(index, item);
            } else if (matchesDeleteNoIndex) {
                System.out.println("Выберите какой элемент удалить");
            } else if (matchesEditNoIndex) {
                System.out.println("Выберите какой элемент редактировать");
            } else if (matchesEditNoItem) {
                System.out.println("Введите имя для редактируемого элемента");
            } else if (matchesBlank) {
                System.out.println("Введите что-нибудь");
            } else {
                System.out.println("Неизвестная команда");
            }
        }
    }
}

