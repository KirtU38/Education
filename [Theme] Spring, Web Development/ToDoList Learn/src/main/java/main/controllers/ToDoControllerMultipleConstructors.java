package main.controllers;

import main.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//@RestController
@RequestMapping("/todos/")
public class ToDoControllerMultipleConstructors {

    private ToDoService toDoService;
    private String secondObject;

    @Autowired
    public ToDoControllerMultipleConstructors(String secondObject) {
        this.secondObject = secondObject;
    }

    public ToDoControllerMultipleConstructors(ToDoService toDoService, String secondObject) {
        this.toDoService = toDoService;
        this.secondObject = secondObject;
    }
}
// Если у нас только несколько конструкторов, то к нужному нам нужно добавить @Autowired
