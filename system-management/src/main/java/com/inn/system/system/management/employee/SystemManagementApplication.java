package com.inn.system.system.management.employee;

import org.springframework.boot.SpringApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.inn.system.system.management.employee.infrastructure.adapters.out")
public class SystemManagementApplication {

	public static void main(String[] args) {

		SpringApplication.run(SystemManagementApplication.class, args);
	}

}
