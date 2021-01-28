package main.controllers;

import main.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
@RequestMapping("/todos/")
public class ToDoControllerField {

    @Autowired
    private ToDoService toDoService;

}
// Запись через поле не рекомендуется, смотри ToDoController в конце
