package com.example.volot.Repository;

import com.example.volot.Models.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    public List<Employee> findByNameContaining(String name);
    Employee findByUsername(String username);
    Employee findByName(String name);
}
