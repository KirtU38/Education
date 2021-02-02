package main.model;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToDoRepository extends MongoRepository<ToDo, Integer> {
}
