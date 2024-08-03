package com.inn.system.system.management;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.boot.test.context.SpringBootTest;


import com.inn.system.system.management.employee.domain.model.Employee;
import com.inn.system.system.management.employee.domain.port.out.EmployeeRepository;
import com.inn.system.system.management.employee.domain.port.in.EmployeeUseCase;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


import java.util.List;
import java.util.Optional;

@SpringBootTest
public class SystemManagementApplicationTests{
    @InjectMocks // Assuming you are using Mockito for unit testing
    private EmployeeUseCase employeeUseCase;

    @Mock // Assuming you are using Mockito for unit testing
    private EmployeeRepository employeeRepository;

    private Employee testEmployee;


    @BeforeEach
    void setUp() {
        // Initialisation d'un employé de test avant chaque méthode de test
        testEmployee = new Employee(1L, "John", "IT", "Male", "B.Tech", "john@example.com", "1234567890", "2023-01-01");
    }

    @Test
    void contextLoads() {
        // Vérifie que le contexte de l'application se charge correctement
        assertNotNull(employeeUseCase);
        assertNotNull(employeeRepository);
    }

    @Test
    void testCreateEmployee() {
        // Configuration du mock pour simuler la sauvegarde d'un employé
        when(employeeRepository.save(any(Employee.class))).thenReturn(testEmployee);

        // Appel de la méthode de service pour créer un employé
        Employee createdEmployee = employeeUseCase.createEmployee(testEmployee);

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
        Employee foundEmployee = employeeUseCase.getEmployeeById(1L);

        // Vérifications
        assertNotNull(foundEmployee);
        assertEquals(testEmployee.getId(), foundEmployee.getId());
        verify(employeeRepository, times(1)).findById(1L);
    }

    @Test
    void testUpdateEmployee() {
        // Modification de l'employé de test
        testEmployee.setJobTitle("Senior Developer");

        // Configuration du mock
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(testEmployee));
        when(employeeRepository.save(any(Employee.class))).thenReturn(testEmployee);

        // Appel de la méthode de service pour mettre à jour un employé
        Employee updatedEmployee = employeeUseCase.updateEmployee(1L, testEmployee);

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
        employeeUseCase.deleteEmployee(1L);

        // Vérification que la méthode deleteById a été appelée
        verify(employeeRepository, times(1)).deleteById(1L);
    }

    @Test
    void testGetAllEmployees() {
        // Création d'une liste d'employés de test
        List<Employee> employees = List.of(
                testEmployee = new Employee(2L, "Jane", "HR", "Female", "MBA", "jane@example.com", "0987654321", "2023-01-02")

        );


        // Configuration du mock
        when(employeeRepository.findAll()).thenReturn(employees);

        // Appel de la méthode de service pour récupérer tous les employés
        List<Employee> allEmployees = employeeUseCase.getAllEmployees();

        // Vérifications
        assertNotNull(allEmployees);
        assertEquals(2, allEmployees.size());
        verify(employeeRepository, times(1)).findAll();
    }



}
