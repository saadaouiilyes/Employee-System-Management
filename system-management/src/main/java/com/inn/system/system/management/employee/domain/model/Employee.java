package com.inn.system.system.management.employee.domain.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.*;

import jakarta.persistence.Entity;



@Entity
@Data
@NoArgsConstructor

public class Employee {
    // Getters and Setters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)


    private Long id;
    private String name;
    private String jobTitle;
    private Double salary;


    public Employee(long id, String name, String jobTitle, double salary) {
        this.id = id;
        this.name = name;
        this.jobTitle = jobTitle;
        this.salary = salary;
    }
}