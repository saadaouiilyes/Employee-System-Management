package com.inn.system.system.management.rest;

import com.inn.system.system.management.employee.infrastucture.adapters.rest.EmployeeController;
import com.inn.system.system.management.employee.application.services.EmployeeService;
import com.inn.system.system.management.employee.domain.model.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmployeeController.class)
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

    @Test
    void testGetAllEmployees() throws Exception {

        when(employeeService.getAllEmployees()).thenReturn(Arrays.asList(employee1, employee2));

        mockMvc.perform(get("/api/employees"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(Arrays.asList(employee1, employee2))));
    }

    @Test
    void testGetEmployeeById() throws Exception {

        when(employeeService.getEmployeeById(1L)).thenReturn(employee1);

        mockMvc.perform(get("/api/employees/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(employee1)));
    }

    @Test
    public void testCreateEmployee() throws Exception {
        when(employeeService.createEmployee(employee1)).thenReturn(employee1);

        mockMvc.perform(post("/api/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employee1)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(employee1)));
    }

    @Test
    void testUpdateEmployee() throws Exception {

        when(employeeService.updateEmployee(1L, employee1).thenReturn(employee1));

        mockMvc.perform(put("/api/employees/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employee1)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(employee1)));
    }

    @Test
    void testDeleteEmployee() throws Exception {
        mockMvc.perform(delete("/api/employees/1"))
                .andExpect(status().isNoContent());
    }
}
