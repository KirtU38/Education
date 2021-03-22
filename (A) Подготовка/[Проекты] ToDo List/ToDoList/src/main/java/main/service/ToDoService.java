package main.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import main.model.ToDo;
import main.model.ToDoRepository;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class ToDoService {

  ToDoRepository toDoRepository;

  public List<ToDo> getAllToDos() {
    return new ArrayList<>(toDoRepository.findAll());
  }

  public ToDo getToDoById(int id) {
    if (toDoRepository.findById(id).isEmpty()) {
      return null;
    }
    return toDoRepository.findById(id).get();
  }

  public ToDo addToDo(ToDo toDo) {
    toDo.setDate(new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new Date()));
    toDoRepository.save(toDo);
    return toDo;
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

  public void deleteToDo(int id) {
    toDoRepository.deleteById(id);
  }

  public List<ToDo> deleteAllToDos() {
    toDoRepository.deleteAll();
    return toDoRepository.findAll();
  }
}
