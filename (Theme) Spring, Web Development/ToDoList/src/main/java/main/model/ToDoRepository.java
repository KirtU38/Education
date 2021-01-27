package main.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ToDoRepository extends JpaRepository<ToDo, Integer> {
}
