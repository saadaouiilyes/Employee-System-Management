package com.inn.system.system.management.employee.domain.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;

import lombok.*;





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
    private String department;
    private String gender;
    private String degree;
    private String email;
    private String mobile;
    private String joiningDate;


    public Employee(long id, String name, String jobTitle, String departement , String gender, String degree , String email,String mobile) {
        this.id = id;
        this.name = name;
        this.jobTitle = jobTitle;
        this.department = departement;
        this.gender = gender;
        this.degree = degree;
        this.email = email;
        this.mobile = mobile;
        this.joiningDate = joiningDate;

    }


}