package main.service;

import lombok.RequiredArgsConstructor;
import main.model.ToDo;
import main.model.ToDoRepository;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ToDoService {

    private final ToDoRepository toDoRepository;
    private static int staticId = 1;

    public List<ToDo> getAllToDos() {

        return new ArrayList<>(toDoRepository.findAll());
    }

    public ToDo getToDoById(int id) {

        if (toDoRepository.findById(id).isPresent()) {
            return toDoRepository.findById(id).get();
        }
        return null;
    }

    public ToDo addToDo(ToDo toDo) {

        toDo.setDate(new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new Date()));
        toDo.setId(staticId++);
        toDoRepository.save(toDo);
        return toDo;
    }

    public ToDo updateToDoById(int id, String text) {

        ToDo updatedToDo = toDoRepository.findById(id).get();

        updatedToDo.setId(id);
        updatedToDo.setText(text);
        updatedToDo.setDate(new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new Date()));
        toDoRepository.save(updatedToDo);

        return updatedToDo;
    }

    public ToDo deleteToDo(int id) {

        ToDo deletedTodo = toDoRepository.findById(id).get();
        toDoRepository.deleteById(id);
        return deletedTodo;
    }

    public List<ToDo> deleteAllToDos() {

        toDoRepository.deleteAll();
        return toDoRepository.findAll();
    }
}
