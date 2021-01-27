package main.service;

import main.model.ToDo;
import main.model.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ToDoService {

    @Autowired
    private ToDoRepository toDoRepository;

    public List<ToDo> getAllToDos() {

        return new ArrayList<>(toDoRepository.findAll());
    }

    public ToDo getToDoById(int id) {

        if (toDoRepository.findById(id).isPresent()) {
            return toDoRepository.findById(id).get();
        }
        return null;
    }

    public Integer addToDo(ToDo toDo) {

        ToDo newToDo = toDoRepository.save(toDo);
        return newToDo.getId();
    }

    public void updateToDoById(int id, String text) {

        Optional<ToDo> updatedToDo = toDoRepository.findById(id);
        if (updatedToDo.isPresent()) {
            updatedToDo.get().setId(id);
            updatedToDo.get().setText(text);
            toDoRepository.save(updatedToDo.get());
        }
    }

    // Можно использовать и такой вариант
    /*@Transactional
    public void updateToDoById(int id, String text) {

        toDoRepository.findById(id).ifPresent(toDo -> toDo.setText(text));
    }*/

    public void deleteToDo(int id) {

        toDoRepository.deleteById(id);
    }

    public List<ToDo> deleteAllToDos() {
        toDoRepository.deleteAll();
        return toDoRepository.findAll();
    }
}
