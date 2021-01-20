package main.ToDoList;

import main.ToDoList.Commands.Add;
import main.ToDoList.Commands.Delete;
import main.ToDoList.Commands.PrintList;
import main.ToDoList.Commands.ToDoListCommand;

import java.util.*;

public class ToDoList {

    private static final Scanner scanner = new Scanner(System.in);
    private static final List<String> toDoList = new ArrayList<>();
    private static final HashMap<String, ToDoListCommand> allCommands = new HashMap<>();

    public void startToDoList() {

        System.out.print("Введите команду:");
        String input = scanner.nextLine();
        String command = input.split("\\s")[0];

        if (allCommands.containsKey(command)) {
            allCommands.get(command).executeCommand(input);
        } else {
            System.out.println("Нет такой команды");
        }

    }

    public static List<String> getToDoList() {
        return toDoList;
    }
}