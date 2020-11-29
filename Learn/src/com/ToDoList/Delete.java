package com.ToDoList;

public class Delete extends ToDoListCommand {

    @Override
    public void executeCommand(String input) {

        String task = input.replaceAll("^\\w+\\s", "");

        ToDoList.getToDoList().remove(task.trim());
        System.out.println("Удалена задача " + task);
    }
}