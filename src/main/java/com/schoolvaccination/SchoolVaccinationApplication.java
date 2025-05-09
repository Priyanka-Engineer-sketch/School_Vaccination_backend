package com.schoolvaccination;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = {"com.schoolvaccination"})
public class SchoolVaccinationApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchoolVaccinationApplication.class, args);
	}

}
