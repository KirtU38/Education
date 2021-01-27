package main.service;

import lombok.RequiredArgsConstructor;
import main.model.ToDo;
import main.model.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ToDoService {

    private final ToDoRepository toDoRepository;

    public List<ToDo> getAllToDos() {

        ToDo toDo1 = new ToDo();
        ToDo toDo2 = new ToDo();
        ToDo toDo3 = new ToDo();
        ToDo toDo4 = new ToDo();
        toDo1.setText("Do laundry");
        toDo2.setText("Go to the gym");
        toDo3.setText("Have a fuck");
        toDo4.setText("Walk around");
        addToDo(toDo1);
        addToDo(toDo2);
        addToDo(toDo3);
        addToDo(toDo4);
        return new ArrayList<>(toDoRepository.findAll());
    }

    public ToDo getToDoById(int id) {

        if (toDoRepository.findById(id).isPresent()) {
            return toDoRepository.findById(id).get();
        }
        return null;
    }

    public Integer addToDo(ToDo toDo) {

        toDo.setDate(new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new Date()));
        toDoRepository.save(toDo);
        return toDo.getId();
    }

    public void updateToDoById(int id, String text) {

        Optional<ToDo> updatedToDo = toDoRepository.findById(id);
        if (updatedToDo.isPresent()) {
            updatedToDo.get().setId(id);
            updatedToDo.get().setText(text);
            updatedToDo.get().setDate(new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new Date()));
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
