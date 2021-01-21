package main;

import main.response.ToDo;

import java.util.*;

public class Storage {

    private static int currentId = 1;
    private static final LinkedHashMap<Integer, ToDo> toDoList = new LinkedHashMap<>();

    public static LinkedHashMap<Integer, ToDo> getAllToDos() {

        return toDoList;
    }

    public static ToDo getToDoById(int id) {

        if (!toDoList.containsKey(id)) {
            return null;
        }
        return toDoList.get(id);
    }

    public static int addToDo(ToDo toDo) {

        toDo.setId(currentId);
        toDoList.put(currentId, toDo);
        return currentId++;
    }

    public static void updateToDoById(int id, ToDo toDo) {

        toDo.setId(id);
        toDoList.put(id, toDo);
    }

    public static void deleteToDo(int id) {

        toDoList.remove(id);
    }

    public static LinkedHashMap<Integer, ToDo> deleteAllToDos() {

        toDoList.clear();
        return toDoList;
    }
}
