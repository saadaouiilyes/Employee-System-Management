package com.inn.system.system.management.employee.application.services;

/*import com.inn.system.system.management.employee.infrastucture.adapters.repository.EmployeeJpaRepository;*/
import com.inn.system.system.management.employee.domain.port.in.EmployeeUseCase;
import com.inn.system.system.management.employee.domain.port.out.EmployeeRepository;
import com.inn.system.system.management.employee.domain.model.Employee;
import com.inn.system.system.management.employee.commun.exception.ResourceNotFoundException;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class EmployeeService implements EmployeeUseCase {



    /*private final  EmployeeJpaRepository employeeJpaRepository;*/
    private final EmployeeRepository employeeRepository;



    @Autowired
    public EmployeeService( EmployeeRepository employeeRepository) {

        this.employeeRepository = employeeRepository;

    }

    @Override
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee updateEmployee(Long id, @NotNull Employee employee) {
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        existingEmployee.setName(employee.getName());
        existingEmployee.setJobTitle(employee.getJobTitle());

        return employeeRepository.save(existingEmployee);
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }


}
