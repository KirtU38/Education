package main.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


// Здесь обычный репозиторий

@Repository
public interface ToDoRepository extends CrudRepository<ToDo, String> {
}
