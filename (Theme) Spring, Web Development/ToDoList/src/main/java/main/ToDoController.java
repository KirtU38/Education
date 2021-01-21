package main;

import main.response.ToDo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;

@RestController
public class ToDoController {

    @GetMapping("/todos/")
    public LinkedHashMap<Integer, ToDo> list() {

        return Storage.getAllToDos();
    }

    @GetMapping("/todos/{id}")
    public ResponseEntity<ToDo> getToDoById(@PathVariable("id") int id) {

        ToDo toDo = Storage.getToDoById(id);
        if (toDo == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity<>(toDo, HttpStatus.OK);
    }

    @PostMapping("/todos/")
    public Integer add(@RequestBody ToDo toDo) {

        return Storage.addToDo(toDo);
    }

    @PutMapping(value = "/todos/{id}")
    public ResponseEntity<ToDo> updateToDoById(@PathVariable("id") int id, @RequestBody ToDo toDo) {

        ToDo OldToDo = Storage.getToDoById(id);
        if (OldToDo == null) {
            return ResponseEntity.notFound().build();
        }
        Storage.updateToDoById(id, toDo);
        return ResponseEntity.ok(toDo);
    }

    @DeleteMapping("/todos/")
    public LinkedHashMap<Integer, ToDo> deleteAll() {

        return Storage.deleteAllToDos();
    }

    @DeleteMapping("/todos/{id}")
    public ResponseEntity<ToDo> delete(@PathVariable("id") int id) {

        ToDo toDo = Storage.getToDoById(id);
        if (toDo == null) {
            return ResponseEntity.notFound().build();
        }
        Storage.deleteToDo(id);
        return ResponseEntity.ok(toDo);
    }

}
