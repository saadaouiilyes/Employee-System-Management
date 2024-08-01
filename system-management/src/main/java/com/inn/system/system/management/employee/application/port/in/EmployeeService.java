package com.inn.system.system.management.employee.application.port.in;

import com.inn.system.system.management.employee.domain.model.Employee;




import java.util.List;


public interface EmployeeService {
        List<Employee> getAllEmployees();
        Employee getEmployeeById(Long Id);
        Employee createEmployee(Employee employee);
        Employee updateEmployee(Long id , Employee employee);
        void deleteEmployee(Long Id);


}
