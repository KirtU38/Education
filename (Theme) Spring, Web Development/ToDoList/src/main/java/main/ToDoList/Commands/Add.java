package main.ToDoList.Commands;

import main.ToDoList.ToDoList;

public class Add extends ToDoListCommand {
    @Override
    public void executeCommand(String input) {

        String task = input.replaceAll("^add\\s", "");

        ToDoList.getToDoList().add(task.trim());
        System.out.println("Добавлена задача " + task);
    }
}
