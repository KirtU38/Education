package main;

import main.response.ToDo;
import java.util.*;

public class Storage {

    private static final LinkedHashMap<Integer, ToDo> toDoList = new LinkedHashMap<>();

    public static LinkedHashMap<Integer, ToDo> getAllToDos() {
        return toDoList;
    }

    public static int addToDo(ToDo toDo) {
        int id = toDoList.size();
        toDo.setId(id);
        toDoList.put(id, toDo);
        return id;
    }

    public static int deleteToDo(int id) {

        toDoList.remove(id);
        return id;
    }

    public static ToDo selectToDoById(int id) {

        return toDoList.get(id);
    }

    public static int updateToDoById(int id, ToDo toDo) {

        toDo.setId(id);
        toDoList.put(id, toDo);
        return id;
    }
}
