package com.inn.system.system.management.services;

import com.inn.system.system.management.employee.application.services.EmployeeService;
import com.inn.system.system.management.employee.domain.port.out.EmployeeRepository;
import com.inn.system.system.management.employee.domain.model.Employee;

import org.jetbrains.annotations.Contract;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;


import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;



    @Test
    void testCreateEmployee() {
        Employee employee = new Employee(0L, "John", "IT", "Male", "B.Tech", "john@example.com", "1234567890", "2023-01-01");
        when(employeeRepository.save(any(Employee.class))).thenReturn(new Employee(1L, "John", "IT", "Male", "B.Tech", "john@example.com", "1234567890", "2023-01-01"));

        Employee createdEmployee = employeeService.createEmployee(employee);

        assertEquals(employee.getName(), createdEmployee.getName());
        verify(employeeRepository, times(1)).save(employee);
    }

    @Test
    void testGetEmployeeById() {
        Employee employee = new Employee(1L, "John", "IT", "Male", "B.Tech", "john@example.com", "1234567890", "2023-01-01");
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        Employee foundEmployee = employeeService.getEmployeeById(1L);

        assertEquals(employee.getName(), foundEmployee.getName());
        verify(employeeRepository, times(1)).findById(1L);
    }

    @Test
    void testGetAllEmployees() {
        List<Employee> employees = Arrays.asList(
         new Employee(1L, "John", "IT", "Male", "B.Tech", "john@example.com", "1234567890", "2023-01-01"),
         new Employee(2L, "Jane", "HR", "Female", "MBA", "jane@example.com", "0987654321", "2023-01-02")
        );
        when(employeeRepository.findAll()).thenReturn(employees);

        List<Employee> result = employeeService.getAllEmployees();
        assertEquals(2, result.size());
        assertNotNull(result);
        verify(employeeRepository, times(1)).findAll();
    }




    @Contract(pure = true)
    private void assertNotNull(List<Employee> ignoredResult) {
    }

    @Test
    void testUpdateEmployee() {
        //Arrange
        Employee existingEmployee = new Employee(1L, "John", "IT", "Male", "B.Tech", "john@example.com", "1234567890", "2023-01-01");
        Employee updatedEmployee = new Employee(1L, "John Doe", "IT", "Male", "B.Tech", "john@example.com", "1234567890", "2023-01-01");

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(existingEmployee));
        when(employeeRepository.save(any(Employee.class))).thenReturn(updatedEmployee);

        //Act
        Employee result = employeeService.updateEmployee(1L, updatedEmployee);

        //Assert

        assertEquals("John Doe", result.getName());

        //verify
        verify(employeeRepository, times(1)).findById(1L);
        verify(employeeRepository, times(1)).save(updatedEmployee);
    }

    @Test
    void testDeleteEmployee() {
        doNothing().when(employeeRepository).deleteById(1L);

        employeeService.deleteEmployee(1L);

        verify(employeeRepository, times(1)).deleteById(1L);
    }


}
