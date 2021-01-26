package main;

import main.response.ToDo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;

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
        return new ResponseEntity<ToDo>(toDo, HttpStatus.OK);
    }

    @PostMapping
    public Integer add(@RequestBody ToDo toDo) {

        return Storage.addToDo(toDo);
    }

    @PutMapping("{id}")
    public ResponseEntity<ToDo> updateToDoById(@PathVariable("id") int id, @RequestBody ToDo toDo) {

        ToDo OldToDo = Storage.getToDoById(id);
        if (OldToDo == null) {
            return ResponseEntity.notFound().build();
        }
        Storage.updateToDoById(id, toDo);
        return ResponseEntity.ok(toDo);
    }

    @DeleteMapping
    public LinkedHashMap<Integer, ToDo> deleteAll() {

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
