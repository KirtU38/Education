package main.controllers;

import main.service.ToDoService;
import org.springframework.web.bind.annotation.*;

//@RestController
@RequestMapping("/todos/")
public class ToDoControllerSingleConstructor {

    private final ToDoService toDoService;

    public ToDoControllerSingleConstructor(ToDoService toDoService) {
        this.toDoService = toDoService;
    }
}
// Если у нас только однин конструктор, то к нему автоматом добавляетя @Autowired
