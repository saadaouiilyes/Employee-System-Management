package com.inn.system.system.management;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.boot.test.context.SpringBootTest;


import com.inn.system.system.management.employee.domain.model.Employee;
import com.inn.system.system.management.employee.application.port.out.EmployeeRepository;
import com.inn.system.system.management.employee.application.port.in.EmployeeService;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class SystemManagementApplicationTests{
    @InjectMocks // Assuming you are using Mockito for unit testing
    private EmployeeService employeeService;

    @Mock // Assuming you are using Mockito for unit testing
    private EmployeeRepository employeeRepository;

    private Employee testEmployee;


    @BeforeEach
    void setUp() {
        // Initialisation d'un employé de test avant chaque méthode de test
        testEmployee = new Employee(1L, "John Doe", "Developer", 50000.0);
    }

    @Test
    void contextLoads() {
        // Vérifie que le contexte de l'application se charge correctement
        assertNotNull(employeeService);
        assertNotNull(employeeRepository);
    }

    @Test
    void testCreateEmployee() {
        // Configuration du mock pour simuler la sauvegarde d'un employé
        when(employeeRepository.save(any(Employee.class))).thenReturn(testEmployee);

        // Appel de la méthode de service pour créer un employé
        Employee createdEmployee = employeeService.createEmployee(testEmployee);

        // Vérifications
        assertNotNull(createdEmployee);
        assertEquals(testEmployee.getName(), createdEmployee.getName());
        verify(employeeRepository, times(1)).save(any(Employee.class));
    }

    @Test
    void testGetEmployeeById() {
        // Configuration du mock pour simuler la récupération d'un employé par ID
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(testEmployee));

        // Appel de la méthode de service pour récupérer un employé
        Employee foundEmployee = employeeService.getEmployeeById(1L);

        // Vérifications
        assertNotNull(foundEmployee);
        assertEquals(testEmployee.getId(), foundEmployee.getId());
        verify(employeeRepository, times(1)).findById(1L);
    }

    @Test
    void testUpdateEmployee() {
        // Modification de l'employé de test
        testEmployee.setPosition("Senior Developer");

        // Configuration du mock
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(testEmployee));
        when(employeeRepository.save(any(Employee.class))).thenReturn(testEmployee);

        // Appel de la méthode de service pour mettre à jour un employé
        Employee updatedEmployee = employeeService.updateEmployee(1L, testEmployee);

        // Vérifications
        assertNotNull(updatedEmployee);
        assertEquals("Senior Developer", updatedEmployee.getJobTitle());
        verify(employeeRepository, times(1)).findById(1L);
        verify(employeeRepository, times(1)).save(any(Employee.class));
    }

    @Test
    void testDeleteEmployee() {
        // Configuration du mock
        doNothing().when(employeeRepository).deleteById(1L);

        // Appel de la méthode de service pour supprimer un employé
        employeeService.deleteEmployee(1L);

        // Vérification que la méthode deleteById a été appelée
        verify(employeeRepository, times(1)).deleteById(1L);
    }

    @Test
    void testGetAllEmployees() {
        // Création d'une liste d'employés de test
        List<Employee> employees = Arrays.asList(
                testEmployee,
                new Employee(2L, "Jane Smith", "Manager", 70000.0)
        );

        // Configuration du mock
        when(employeeRepository.findAll()).thenReturn(employees);

        // Appel de la méthode de service pour récupérer tous les employés
        List<Employee> allEmployees = employeeService.getAllEmployees();

        // Vérifications
        assertNotNull(allEmployees);
        assertEquals(2, allEmployees.size());
        verify(employeeRepository, times(1)).findAll();
    }
}
