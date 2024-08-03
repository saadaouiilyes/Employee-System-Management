package com.inn.system.system.management.employee.infrastucture.adapters.repository;


import com.inn.system.system.management.employee.domain.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository

public interface EmployeeJpaRepository extends JpaRepository<Employee, Long>{
}
