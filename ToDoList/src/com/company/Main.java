package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        while (true) {

            TodoListManager manager = new TodoListManager();
            Scanner scanner = new Scanner(System.in);

            System.out.print("Введите команду: ");

            String input = scanner.nextLine().trim();
            String[] words = input.split("\\s+", 3);
            String command = words[0];
            int index = -1;

            if(input.matches("")){
                System.out.println("Введено пустое поле");
                continue;
            }
            if (input.equals("list") || input.equals("ls")) {
                manager.printItems();
                continue;
            }

            if (words[1].matches("-?\\d+")) {
                index = Integer.parseInt(words[1]);
            }

            if (command.equals("add")) {
                String item = (index == -1) ? input.split("\\s+", 2)[1] : words[2];
                manager.addItem(index, item);
            } else if (command.equals("delete")) {
                manager.deleteItem(index);
            } else if (command.equals("edit")) {
                String item = words[2];
                manager.editItem(index, item);
            } else {
                System.out.println("Неизвестная команда");
            }
        }
    }
}

