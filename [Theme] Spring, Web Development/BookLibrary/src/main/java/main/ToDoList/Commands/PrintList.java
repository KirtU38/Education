package main.ToDoList.Commands;

import main.ToDoList.ToDoList;

public class PrintList extends ToDoListCommand {

    @Override
    public void executeCommand(String command) {

        //ToDoList.getToDoList().forEach(System.out::println);
        System.out.println(ToDoList.getToDoList());
    }
}
