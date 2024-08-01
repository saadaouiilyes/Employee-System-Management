package com.inn.system.system.management.employee.infrastructure.adapters.out;

import com.inn.system.system.management.employee.application.port.out.EmployeeRepository;
import com.inn.system.system.management.employee.domain.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository

public interface EmployeeJpaRepository extends JpaRepository<Employee, Long>, EmployeeRepository{
}
