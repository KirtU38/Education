package main.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToDoRepository extends JpaRepository<ToDo, Integer>  {
}
// Здесь можно ещё CrudRepository, но он в методе findAll() возвращает не List а Iterable, плюс JpaRepository более
// навороченный сабкласс CrudRepository
