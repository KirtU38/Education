package ru.beloshitsky.todolist.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import ru.beloshitsky.todolist.model.ToDo;
import ru.beloshitsky.todolist.service.ToDoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/todos/")
@RestController
public class ToDoController {

  ToDoService toDoService;

  @GetMapping
  public List<ToDo> list() {
    return toDoService.getAllToDos();
  }

  @GetMapping("{id}")
  public ResponseEntity<ToDo> getToDoById(@PathVariable("id") int id) {
    ToDo toDo = toDoService.getToDoById(id);
    if (toDo == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(toDo);
  }

  @PostMapping
  public ResponseEntity<ToDo> add(ToDo toDo) {
    ToDo addedToDo = toDoService.addToDo(toDo);
    return ResponseEntity.ok(addedToDo);
  }

  @PutMapping("{id}")
  public ResponseEntity<ToDo> updateToDoById(
      @PathVariable("id") int id, @RequestParam String text) {

    ToDo replacedToDo = toDoService.getToDoById(id);
    if (replacedToDo == null) {
      return ResponseEntity.notFound().build();
    }
    toDoService.updateToDoById(id, text);
    return ResponseEntity.ok(replacedToDo);
  }

  @DeleteMapping
  public ResponseEntity<List<ToDo>> deleteAll() {
    List<ToDo> listAfterClearing = toDoService.deleteAllToDos();
    if (listAfterClearing.isEmpty()) {
      return ResponseEntity.ok(listAfterClearing);
    }
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
  }

  @DeleteMapping("{id}")
  public ResponseEntity<ToDo> delete(@PathVariable("id") int id) {
    ToDo toDo = toDoService.getToDoById(id);
    if (toDo == null) {
      return ResponseEntity.notFound().build();
    }
    toDoService.deleteToDo(id);
    return ResponseEntity.ok(toDo);
  }
}
