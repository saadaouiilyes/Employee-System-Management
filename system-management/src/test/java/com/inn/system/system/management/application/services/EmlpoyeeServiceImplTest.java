package com.inn.system.system.management.application.services;

import com.inn.system.system.management.employee.application.services.EmployeeServiceImpl;
import com.inn.system.system.management.employee.application.port.out.EmployeeRepository;
import com.inn.system.system.management.employee.domain.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateEmployee() {
        Employee employee = new Employee(1L, "John Doe", "Developer", 50000.0);
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        Employee createdEmployee = employeeService.createEmployee(employee);

        assertEquals(employee.getName(), createdEmployee.getName());
        verify(employeeRepository, times(1)).save(employee);
    }

    @Test
    void testGetEmployeeById() {
        Employee employee = new Employee(1L, "John Doe", "Developer", 50000.0);
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        Employee foundEmployee = employeeService.getEmployeeById(1L);

        assertEquals(employee.getName(), foundEmployee.getName());
        verify(employeeRepository, times(1)).findById(1L);
    }

    @Test
    void testGetAllEmployees() {
        Employee employee1 = new Employee(1L, "John Doe", "Developer", 50000.0);
        Employee employee2 = new Employee(2L, "Jane Doe", "Manager", 60000.0);
        when(employeeRepository.findAll()).thenReturn(Arrays.asList(employee1, employee2));

        assertEquals(2, employeeService.getAllEmployees().size());
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    void testUpdateEmployee() {
        //Arrange
        Employee existingEmployee = new Employee(1L, "John Doe", "Developer", 50000.0);
        Employee updatedEmployee = new Employee(1L, "John Doe", "Senior Developer", 70000.0);


        Mockito.when(employeeRepository.findById(1L)).thenReturn(Optional.of(existingEmployee));
        Mockito.when(employeeRepository.save(any(Employee.class))).thenReturn(updatedEmployee);

        //Act
        Employee result = employeeService.updateEmployee(1L, updatedEmployee);

        //Assert
        assertEquals("Senior Developer", result.getJobTitle());
        assertEquals(70000.0, result.getSalary());


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
