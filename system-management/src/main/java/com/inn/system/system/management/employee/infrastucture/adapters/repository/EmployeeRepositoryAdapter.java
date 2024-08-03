package com.inn.system.system.management.employee.infrastucture.adapters.repository;

import com.inn.system.system.management.employee.domain.model.Employee;
import com.inn.system.system.management.employee.domain.port.out.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepositoryAdapter implements EmployeeRepository {
    private final EmployeeJpaRepository employeeJpaRepository;

    @Autowired
    public EmployeeRepositoryAdapter(EmployeeJpaRepository employeeJpaRepository) {
        this.employeeJpaRepository = employeeJpaRepository;
    }

    @Override
    public List<Employee> findAll() {
        return employeeJpaRepository.findAll();
    }

    @Override
    public Optional<Employee> findById(Long id) {
        return employeeJpaRepository.findById(id);
    }

    @Override
    public Employee save(Employee employee) {
        return employeeJpaRepository.save(employee);
    }

    @Override
    public void deleteById(Long id) {
        employeeJpaRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return employeeJpaRepository.existsById(id);
    }
}
