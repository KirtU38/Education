package main;

import lombok.RequiredArgsConstructor;
import main.model.ToDo;
import main.model.ToDoRepository;
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

        // Здесь мы создаем переменные "todos" и "todosCount" которые потом сможем использовать в index.html
        // как параметры для Thymeleaf(сказали его редко щас используют)
        model.addAttribute("todos", toDoList);
        model.addAttribute("todosCount", toDoList.size());
        return "index";
        // здесь не надо подписывать .html потому что он и так понимает(в конфиге вроде задано это)
    }
}
