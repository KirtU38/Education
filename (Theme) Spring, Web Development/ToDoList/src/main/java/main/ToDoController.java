package main;

import main.response.ToDo;
import org.springframework.web.bind.annotation.*;
import java.util.LinkedHashMap;

@RestController
public class ToDoController {

    @GetMapping(value = "/todos/")
    public LinkedHashMap<Integer, ToDo> list() {

        return Storage.getAllToDos();
    }

    @PostMapping(value = "/todos/")
    public int add(@RequestBody ToDo toDo) {

        return Storage.addToDo(toDo);
    }

    @GetMapping(value = "/todos/{id}")
    public ToDo getToDoById(@PathVariable("id") int id) {

        return Storage.selectToDoById(id);
    }

    @DeleteMapping(value = "/todos/{id}")
    public int delete(@PathVariable("id") int id) {

        return Storage.deleteToDo(id);
    }

    @PutMapping(value = "/todos/{id}")
    public int updateToDoById(@PathVariable("id") int id, @RequestBody ToDo toDo) {

        return Storage.updateToDoById(id, toDo);
    }

}
