package main.controllers;

import lombok.RequiredArgsConstructor;
import main.model.ToDo;
import main.service.ToDoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/todos/")
@RequiredArgsConstructor
public class ToDoController {

    private final ToDoService toDoService;

    @GetMapping
    public List<ToDo> list() {

        return toDoService.getAllToDos();
    }

    @GetMapping("{id}")
    public ToDo getToDoById(@PathVariable("id") int id) {

        return toDoService.getToDoById(id);
    }

    @PostMapping
    public ToDo add(ToDo toDo) {

        return toDoService.addToDo(toDo);
    }

    @PutMapping("{id}")
    public ToDo updateToDoById(@PathVariable("id") int id, @RequestParam String text) {

        return toDoService.updateToDoById(id, text);
    }

    @DeleteMapping
    public List<ToDo> deleteAll() {

        return toDoService.deleteAllToDos();
    }

    @DeleteMapping("{id}")
    public ToDo delete(@PathVariable("id") int id) {

        return toDoService.deleteToDo(id);
    }
}

