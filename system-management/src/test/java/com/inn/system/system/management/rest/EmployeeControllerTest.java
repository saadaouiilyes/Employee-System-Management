package com.inn.system.system.management.rest;

import com.inn.system.system.management.employee.commun.exception.ResourceNotFoundException;
import com.inn.system.system.management.employee.infrastucture.adapters.rest.EmployeeController;
import com.inn.system.system.management.employee.application.services.EmployeeService;
import com.inn.system.system.management.employee.domain.model.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmployeeController.class)
@DisplayName("Employee Controller Tests")
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @Autowired
    private ObjectMapper objectMapper;

    private Employee employee1;
    private Employee employee2;

    @BeforeEach
    public void setUp() {
        employee1 = new Employee(1L, "John", "IT", "Male", "B.Tech", "john@example.com", "1234567890", "2023-01-01");
        employee2 = new Employee(2L, "Jane", "HR", "Female", "MBA", "jane@example.com", "0987654321", "2023-01-02");
    }

    @Nested
    @DisplayName("GET /api/employees")
    class GetAllEmployees {
        @Test
        @DisplayName("should return all employees")
        void testGetAllEmployees() throws Exception {
            when(employeeService.getAllEmployees()).thenReturn(Arrays.asList(employee1, employee2));

            mockMvc.perform(get("/api/employees"))
                    .andExpect(status().isOk())
                    .andExpect(content().json(objectMapper.writeValueAsString(Arrays.asList(employee1, employee2))));
        }
    }

    @Nested
    @DisplayName("GET /api/employees/{id}")
    class GetEmployeeById {
        @Test
        @DisplayName("should return employee when found")
        void testGetEmployeeById() throws Exception {
            when(employeeService.getEmployeeById(1L)).thenReturn(employee1);

            mockMvc.perform(get("/api/employees/1"))
                    .andExpect(status().isOk())
                    .andExpect(content().json(objectMapper.writeValueAsString(employee1)));
        }

        @Test
        @DisplayName("should return 404 when employee not found")
        void testGetEmployeeByIdNotFound() throws Exception {
            when(employeeService.getEmployeeById(999L)).thenThrow(new ResourceNotFoundException("Employee not found"));

            mockMvc.perform(get("/api/employees/999"))
                    .andExpect(status().isNotFound());
        }
    }

    @Nested
    @DisplayName("POST /api/employees")
    class CreateEmployee {
        @Test
        @DisplayName("should create new employee")
        void testCreateEmployee() throws Exception {
            when(employeeService.createEmployee(any(Employee.class))).thenReturn(employee1);

            mockMvc.perform(post("/api/employees")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(employee1)))
                    .andExpect(status().isOk())
                    .andExpect(content().json(objectMapper.writeValueAsString(employee1)));
        }

        @Test
        @DisplayName("should return 400 when invalid data")
        void testCreateEmployeeInvalidData() throws Exception {
            Employee invalidEmployee = new Employee(); // Assume this is invalid

            mockMvc.perform(post("/api/employees")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(invalidEmployee)))
                    .andExpect(status().isBadRequest());
        }
    }

    @Nested
    @DisplayName("PUT /api/employees/{id}")
    class UpdateEmployee {
        @Test
        @DisplayName("should update existing employee")
        void testUpdateEmployee() throws Exception {
            when(employeeService.updateEmployee(eq(1L), any(Employee.class))).thenReturn(employee1);

            mockMvc.perform(put("/api/employees/1")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(employee1)))
                    .andExpect(status().isOk())
                    .andExpect(content().json(objectMapper.writeValueAsString(employee1)));
        }

        @Test
        @DisplayName("should return 404 when updating non-existent employee")
        void testUpdateNonExistentEmployee() throws Exception {
            when(employeeService.updateEmployee(eq(999L), any(Employee.class)))
                    .thenThrow(new ResourceNotFoundException("Employee not found"));

            mockMvc.perform(put("/api/employees/999")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(employee1)))
                    .andExpect(status().isNotFound());
        }
    }

    @Nested
    @DisplayName("DELETE /api/employees/{id}")
    class DeleteEmployee {
        @Test
        @DisplayName("should delete existing employee")
        void testDeleteEmployee() throws Exception {
            doNothing().when(employeeService).deleteEmployee(1L);

            mockMvc.perform(delete("/api/employees/1"))
                    .andExpect(status().isNoContent());

            verify(employeeService, times(1)).deleteEmployee(1L);
        }

        @Test
        @DisplayName("should return 404 when deleting non-existent employee")
        void testDeleteNonExistentEmployee() throws Exception {
            doThrow(new ResourceNotFoundException("Employee not found"))
                    .when(employeeService).deleteEmployee(999L);

            mockMvc.perform(delete("/api/employees/999"))
                    .andExpect(status().isNotFound());
        }
    }
}