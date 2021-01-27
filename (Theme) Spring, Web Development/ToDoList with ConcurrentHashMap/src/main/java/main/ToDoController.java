package main;

import main.response.ToDo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/todos/")
public class ToDoController {

    @GetMapping
    public List<ToDo> list() {

        return Storage.getAllToDos();
    }

    @GetMapping("{id}")
    public ResponseEntity<ToDo> getToDoById(@PathVariable("id") int id) {

        ToDo toDo = Storage.getToDoById(id);
        if (toDo == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity<>(toDo, HttpStatus.OK);
    }

    @PostMapping
    public Integer add(@RequestBody ToDo toDo) {

        return Storage.addToDo(toDo);
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateToDoById(@PathVariable("id") int id, @RequestParam String text) {

        ToDo replacedToDo = Storage.getToDoById(id);
        if (replacedToDo == null) {
            return ResponseEntity.notFound().build();
        }
        Storage.updateToDoById(id, text);
        return ResponseEntity.ok(text);
    }

    @DeleteMapping
    public Map<Integer, ToDo> deleteAll() {

        return Storage.deleteAllToDos();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ToDo> delete(@PathVariable("id") int id) {

        ToDo toDo = Storage.getToDoById(id);
        if (toDo == null) {
            return ResponseEntity.notFound().build();
        }
        Storage.deleteToDo(id);
        return ResponseEntity.ok(toDo);
    }

}
