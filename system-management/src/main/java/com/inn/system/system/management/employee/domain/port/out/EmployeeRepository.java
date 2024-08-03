package com.inn.system.system.management.employee.domain.port.out;

import com.inn.system.system.management.employee.domain.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {

    List<Employee> findAll();
    Optional<Employee> findById(Long id);
    Employee save(Employee employee);
    void deleteById(Long id);
    Boolean existsById(Long id);
}
