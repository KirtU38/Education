package main;

import lombok.RequiredArgsConstructor;
import main.model.ToDo;
import main.model.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class DefaultController {

    private final ToDoRepository toDoRepository;

    @RequestMapping("/")
    public String index(Model model) {

        List<ToDo> toDoList = toDoRepository.findAll();

        model.addAttribute("todos", toDoList);
        model.addAttribute("todosCount", toDoList.size());
        return "index";
    }
}
