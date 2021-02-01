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

    public ToDoList() {
        ToDoListCommand add = new Add();
        ToDoListCommand delete = new Delete();
        ToDoListCommand print = new PrintList();

        allCommands.put("add", add);
        allCommands.put("put", add);
        allCommands.put("delete", delete);
        allCommands.put("del", delete);
        allCommands.put("remove", delete);
        allCommands.put("print", print);
        allCommands.put("ls", print);
    }

    public void startToDoList() {

        while (true) {
            System.out.print("Введите команду:");
            String input = scanner.nextLine();
            String command = input.split("\\s")[0];

            if (allCommands.containsKey(command)) {
                allCommands.get(command).executeCommand(input);
            } else {
                System.out.println("Нет такой команды");
            }
        }
    }

    public static List<String> getToDoList() {
        return toDoList;
    }
}