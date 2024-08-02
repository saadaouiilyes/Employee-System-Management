package com.inn.system.system.management.infrastructure.adapters.in;

import com.inn.system.system.management.employee.infrastructure.adapters.in.EmployeeController;
import com.inn.system.system.management.employee.application.services.EmployeeServiceImpl;
import com.inn.system.system.management.employee.domain.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @BeforeEach
    void setUp(WebApplicationContext webApplicationContext) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void testCreateEmployee() throws Exception {
        Employee employee = new Employee(1L, "John Doe", "Developer", 50000.0);
        when(employeeService.createEmployee(any(Employee.class))).thenReturn(employee);

        mockMvc.perform(post("/api/employees")
                        .contentType("application/json")
                        .content("{\"name\": \"John Doe\", \"position\": \"Developer\", \"salary\": 50000}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"));
    }

    @Test
    void testGetEmployeeById() throws Exception {
        Employee employee = new Employee(1L, "John Doe", "Developer", 50000.0);
        when(employeeService.getEmployeeById(1L)).thenReturn(employee);

        mockMvc.perform(get("/api/employees/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"));
    }

    @Test
    void testGetAllEmployees() throws Exception {
        Employee employee1 = new Employee(1L, "John Doe", "Developer", 50000.0);
        Employee employee2 = new Employee(2L, "Jane Doe", "Manager", 60000.0);
        when(employeeService.getAllEmployees()).thenReturn(Arrays.asList(employee1, employee2));

        mockMvc.perform(get("/api/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void testUpdateEmployee() throws Exception {
        Employee updatedEmployee = new Employee(1L, "John Doe", "Senior Developer", 70000.0);
        when(employeeService.updateEmployee(any(Long.class), any(Employee.class))).thenReturn(updatedEmployee);

        mockMvc.perform(put("/api/employees/1")
                        .contentType("application/json")
                        .content("{\"name\": \"John Doe\", \"position\": \"Senior Developer\", \"salary\": 70000}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.position").value("Senior Developer"));
    }

    @Test
    void testDeleteEmployee() throws Exception {
        mockMvc.perform(delete("/api/employees/1"))
                .andExpect(status().isNoContent());
    }
}
