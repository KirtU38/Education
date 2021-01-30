package main.service;

import lombok.RequiredArgsConstructor;
import main.model.ToDo;
import main.model.ToDoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ToDoService {

    private static int staticId = 1;
    private final ToDoRepository toDoRepository;

    public List<ToDo> getAllToDos() {

        Iterable<ToDo> result = toDoRepository.findAll();
        List<ToDo> toDoList = new ArrayList<>();
        result.forEach(toDoList::add);
        return toDoList;
    }

    public ToDo getToDoById(String id) {


        Optional<ToDo> todo = toDoRepository.findById(id);
        return todo.get();
    }

    public ToDo addToDo(ToDo toDo) {

        ToDo newToDo = new ToDo(String.valueOf(staticId),toDo.getText());
        toDoRepository.save(newToDo);
        staticId++;
        return newToDo;
    }

    public ToDo updateToDoById(String id, String text) {


        ToDo changedTodo = toDoRepository.findById(id).get();
        changedTodo.setText(text);
        toDoRepository.save(changedTodo);
        return changedTodo;
    }

    public List<ToDo> deleteAllToDos() {

        List<ToDo> todoList = new ArrayList<>();
        Iterable<ToDo> all = toDoRepository.findAll();
        all.forEach(todoList::add);

        toDoRepository.deleteAll();
        return todoList;
    }

    public ToDo deleteToDo(String id) {

        ToDo deletedTodo = toDoRepository.findById(id).get();
        toDoRepository.deleteById(id);
        return deletedTodo;
    }
}
