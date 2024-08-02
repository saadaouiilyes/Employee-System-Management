package com.inn.system.system.management.employee.application.services;

import com.inn.system.system.management.employee.infrastructure.adapters.out.EmployeeJpaRepository;
import com.inn.system.system.management.employee.application.port.in.EmployeeService;
import com.inn.system.system.management.employee.application.port.out.EmployeeRepository;
import com.inn.system.system.management.employee.domain.model.Employee;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {



    private final  EmployeeJpaRepository employeeJpaRepository;
    private final EmployeeRepository employeeRepository;



    @Autowired
    public EmployeeServiceImpl(EmployeeJpaRepository employeeJpaRepository , EmployeeRepository employeeRepository) {
        this.employeeJpaRepository = employeeJpaRepository;
        this.employeeRepository = employeeRepository;

    }

    @Override
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeJpaRepository.findAll();
    }

    @Override
    public Employee updateEmployee(Long id, @org.jetbrains.annotations.NotNull Employee employee) {
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        existingEmployee.setName(employee.getName());
        existingEmployee.setJobTitle(employee.getJobTitle());
        employee.setSalary(existingEmployee.getSalary());
        return employeeRepository.save(existingEmployee);
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }


}
