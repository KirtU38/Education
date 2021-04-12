package ru.beloshitsky.todolist.controller;



import lombok .AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.beloshitsky.todolist.model.ToDo;
import ru.beloshitsky.todolist.model.ToDoRepository;

import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Controller
public class MainController {

  ToDoRepository toDoRepository;

  @RequestMapping("/")
  public String index(Model model) {
    List<ToDo> toDoList = toDoRepository.findAll();

    model.addAttribute("todos", toDoList);
    model.addAttribute("todosCount", toDoList.size());
    return "index";
  }
}
