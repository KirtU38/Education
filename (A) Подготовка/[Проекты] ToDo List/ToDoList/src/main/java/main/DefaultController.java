package main;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import main.model.ToDo;
import main.model.ToDoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Controller
public class DefaultController {

    ToDoRepository toDoRepository;

    @RequestMapping("/")
    public String index(Model model) {

        List<ToDo> toDoList = toDoRepository.findAll();

        model.addAttribute("todos", toDoList);
        model.addAttribute("todosCount", toDoList.size());
        return "index";
    }
}
