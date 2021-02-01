package main;

import main.response.ToDo;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Storage {

    private static int currentId = 1;
    private static final Map<Integer, ToDo> toDoMap = new ConcurrentHashMap<>();

    public static List<ToDo> getAllToDos() {

        List<ToDo> toDoList = new ArrayList<>(toDoMap.values());
        return toDoList;
    }

    public static ToDo getToDoById(int id) {

        if (!toDoMap.containsKey(id)) {
            return null;
        }
        return toDoMap.get(id);
    }

    public static Integer addToDo(ToDo toDo) {

        toDo.setId(currentId);
        toDoMap.put(currentId, toDo);
        return currentId++;
    }

    public static void updateToDoById(int id, String text) {

        ToDo toDo = toDoMap.get(id);
        toDo.setId(id);
        toDo.setText(text);
        toDoMap.put(id, toDo);
    }

    public static void deleteToDo(int id) {

        toDoMap.remove(id);
    }

    public static Map<Integer, ToDo> deleteAllToDos() {

        toDoMap.clear();
        return toDoMap;
    }
}
