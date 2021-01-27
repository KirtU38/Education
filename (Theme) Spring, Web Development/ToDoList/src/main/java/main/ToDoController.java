package main;

import main.model.ToDo;
import main.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/todos/")
public class ToDoController {

    @Autowired
    private ToDoService toDoService;

    @GetMapping
    public List<ToDo> list() {

        return toDoService.getAllToDos();
    }

    @GetMapping("{id}")
    public ResponseEntity<ToDo> getToDoById(@PathVariable("id") int id) {

        ToDo toDo = toDoService.getToDoById(id);
        if (toDo == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(toDo);
    }

    @PostMapping
    public Integer add(@RequestBody ToDo toDo) {

        return toDoService.addToDo(toDo);
    }

    @PutMapping("{id}")
    public ResponseEntity<ToDo> updateToDoById(@PathVariable("id") int id, @RequestParam String text) {

        ToDo replacedToDo = toDoService.getToDoById(id);
        if (replacedToDo == null) {
            return ResponseEntity.notFound().build();
        }
        toDoService.updateToDoById(id, text);
        return ResponseEntity.ok(replacedToDo);
    }

    @DeleteMapping
    public List<ToDo> deleteAll() {

        return toDoService.deleteAllToDos();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ToDo> delete(@PathVariable("id") int id) {

        ToDo toDo = toDoService.getToDoById(id);
        if (toDo == null) {
            return ResponseEntity.notFound().build();
        }
        toDoService.deleteToDo(id);
        return ResponseEntity.ok(toDo);
    }

}
