package ru.beloshitsky.todolist.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.beloshitsky.todolist.advices.annotations.LogArgsAndRetval;
import ru.beloshitsky.todolist.advices.annotations.LogRetval;
import ru.beloshitsky.todolist.model.ToDo;
import ru.beloshitsky.todolist.service.ToDoService;

import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/todos/")
@RestController
public class ToDoController {

  ToDoService toDoService;

  @LogRetval
  @GetMapping
  public List<ToDo> getAll() {
    return toDoService.getAllToDos();
  }
  
  @LogArgsAndRetval
  @GetMapping("{id}")
  public ResponseEntity<ToDo> getById(@PathVariable("id") int id) {
    ToDo toDo = toDoService.getToDo(id);
    if (toDo == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(toDo);
  }
  
  @LogArgsAndRetval
  @PostMapping
  public ResponseEntity<ToDo> post(ToDo toDo) {
    ToDo addedToDo = toDoService.addToDo(toDo);
    return ResponseEntity.ok(addedToDo);
  }

  @LogArgsAndRetval
  @PatchMapping("{id}")
  public ResponseEntity<ToDo> patch(@PathVariable("id") int id, String text) {
    ToDo newToDo = toDoService.updateToDo(id, text);
    if (newToDo == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(newToDo);
  }

  @LogRetval
  @DeleteMapping
  public ResponseEntity<List<ToDo>> deleteAll() {
    List<ToDo> listAfterClearing = toDoService.deleteAllToDos();
    return ResponseEntity.ok(listAfterClearing);
  }

  @LogArgsAndRetval
  @DeleteMapping("{id}")
  public ResponseEntity<ToDo> deleteById(@PathVariable("id") int id) {
    ToDo toDo = toDoService.getToDo(id);
    if (toDo == null) {
      return ResponseEntity.notFound().build();
    }
    toDoService.deleteToDo(id);
    return ResponseEntity.ok(toDo);
  }
}
