package main.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    void deleteEmployeeById(Long id);
    Optional<Employee> findEmployeeById(Long id);
}
