package com.inn.system.system.management.employee.domain.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;


@Getter
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    // Getters and Setters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String jobTitle;
    private Double salary;



    // Constructors

    public Employee(Long id, String name,  String jobTitle, Double salary) {
        this.id = id;
        this.jobTitle = jobTitle;
        this.name = name;
        this.salary = salary;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
    public void getJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
}
