package main.controllers;

import lombok.RequiredArgsConstructor;
import main.model.ToDo;
import main.service.ToDoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/todos/")
@RequiredArgsConstructor
public class ToDoController {

    // Такое создание через сингл конструктор, который мы скрываем в lombok аннотации @RequiredArgsConstructor которая
    // создает конструктор для всех final обьектов - САМАЯ ЛУЧШАЯ
    // Так как при создании контроллера джава обратится к конструктору контроллера, и toDoService будет 100% создан

    private final ToDoService toDoService;

    @GetMapping
    public List<ToDo> list() {

        return toDoService.getAllToDos();
    }

    // @GetMapping("{id}") говорит "Всё что будет после "/todos/" будет считаться переменной, которую мы назовем id"
    // @PathVariable("id") говорит "В переменную нашего метода "int id" прийдет то, что будет гаписано после "/todos/",
    // и название переменной внутри @PathVariable("id") должно совпадать с названием в @GetMapping("{id}")
    @GetMapping("{id}")
    public ResponseEntity<ToDo> getToDoById(@PathVariable("id") int id) {

        ToDo toDo = toDoService.getToDoById(id);
        if (toDo == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(toDo);
    }

    @PostMapping
    public ToDo add(ToDo toDo) {

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

/*
* Структура любого REST контроллера заключается в слоях:
*
* @RestController
* 1) Controller - это самый близкий слой, в котором осуществляется весь маппинг и в нем лучше содержать минимум логики,
* в его методах он просто пользуется готовыми методами с готовой логикой их Service.
* В нем создается обьект Service, в котором содержится вся логика(почти вся).
*
* @Service
* 2) Service - слой который работает с логикой всех запросов, он работает с Repository слоем(база данных) и
* соответственно в нем создается обьект Repository
*
* @Repository
* 3) Repository - это интерфейс. Если мы работаем с базой данных, то это просто слой который extends JpaRepository
* и в нем уже есть все нужные методы.
*
* 4) Entity - это собственно обьект, с которым мы работаем во всем контроллера, будь то User или Book.
* Из него автоматом можно создать таблицу в базе данных.
* Если к нам в POST приходит допустим не param, а JSON обьект, то можно в Контроллере сделать параметры
* (@RequestBody User user) и в конструкторе нашего Entity подписать (@JsonProperty String name), тогда при запросе:
* {
*     "name": "Egor"
* }
* Всё сработает и name нашего обьекта будет взять с Body нашего запроса(это можно выбирать в Postman например там есть
* отдельная вкладка Body)
*
*
*
*
*
*
*
*
*
*
*
* */

