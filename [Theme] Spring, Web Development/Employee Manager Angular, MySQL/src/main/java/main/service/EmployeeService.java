package main.service;

import lombok.RequiredArgsConstructor;
import main.model.Employee;
import main.model.EmployeeRepository;
import main.exceptions.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public List<Employee> findAllEmployees() {

        return new ArrayList<>(employeeRepository.findAll());
    }

    public Employee findEmployeeById(Long id) {

        return employeeRepository.findEmployeeById(id).orElseThrow(()-> new UserNotFoundException("Used by id " + id + " not found"));
    }

    public Employee addEmployee(Employee employee) {

        employee.setEmployeeCode(UUID.randomUUID().toString());
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Employee employee) {

        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Long id) {

        employeeRepository.deleteEmployeeById(id);
    }
}
