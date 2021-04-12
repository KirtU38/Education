package ru.beloshitsky.todolist.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.beloshitsky.todolist.model.ToDo;
import ru.beloshitsky.todolist.model.ToDoRepository;

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

  public ToDo getToDo(int id) {
    Optional<ToDo> toDo = toDoRepository.findById(id);
    return toDo.orElse(null);
  }

  public ToDo addToDo(ToDo toDo) {
    toDo.setDate(new Date());
    toDoRepository.save(toDo);
    return toDo;
  }

  public ToDo updateToDo(int id, String text) {
    Optional<ToDo> updatedToDo = toDoRepository.findById(id);
    if (updatedToDo.isPresent()) {
      ToDo newToDo = updatedToDo.get();
      newToDo.setId(id);
      newToDo.setText(text);
      newToDo.setDate(new Date());
      toDoRepository.save(newToDo);
      return newToDo;
    }
    return null;
  }

  public void deleteToDo(int id) {
    toDoRepository.deleteById(id);
  }

  public List<ToDo> deleteAllToDos() {
    toDoRepository.deleteAll();
    return toDoRepository.findAll();
  }
}
