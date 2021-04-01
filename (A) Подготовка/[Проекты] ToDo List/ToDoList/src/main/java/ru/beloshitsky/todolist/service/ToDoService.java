package ru.beloshitsky.todolist.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import ru.beloshitsky.todolist.model.ToDo;
import ru.beloshitsky.todolist.model.ToDoRepository;
import org.springframework.stereotype.Service;

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
    Optional<ToDo> toDo = toDoRepository.findById(id);
    if (toDo.isEmpty()) {
      return null;
    }
    return toDo.get();
  }

  public ToDo addToDo(ToDo toDo) {
    toDo.setDate(new Date());
    toDoRepository.save(toDo);
    return toDo;
  }

  public void updateToDoById(int id, String text) {
    Optional<ToDo> updatedToDo = toDoRepository.findById(id);
    if (updatedToDo.isPresent()) {
      ToDo toDo = updatedToDo.get();
      toDo.setId(id);
      toDo.setText(text);
      toDo.setDate(new Date());
      toDoRepository.save(toDo);
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
