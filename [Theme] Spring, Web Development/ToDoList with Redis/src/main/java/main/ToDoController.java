package main;

import lombok.RequiredArgsConstructor;
import main.model.ToDo;
import main.service.ToDoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


// По сути здесь всё то же самое

@RestController
@RequestMapping("/todos/")
@RequiredArgsConstructor
public class ToDoController {

    private static int staticId = 1;
    private final ToDoService toDoService;

    @GetMapping
    public List<ToDo> list() {
        return toDoService.getAllToDos();
    }

    @GetMapping("{id}")
    public ToDo get(@PathVariable("id") String id) {
        return toDoService.getToDoById(id);
    }

    @PostMapping
    public ToDo add(ToDo toDo) {
        return toDoService.addToDo(toDo);
    }

    @PutMapping("{id}")
    public ToDo update(@PathVariable String id, String text) {

        return toDoService.updateToDoById(id, text);
    }

    @DeleteMapping
    public List<ToDo> deleteAll() {

        return toDoService.deleteAllToDos();
    }

    @DeleteMapping("{id}")
    public ToDo delete(@PathVariable("id") String id) {

        return toDoService.deleteToDo(id);
    }
}
