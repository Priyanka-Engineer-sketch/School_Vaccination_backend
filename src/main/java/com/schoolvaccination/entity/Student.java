package com.schoolvaccination.entity;

import java.time.LocalDate;

import org.springframework.data.mongodb.core.mapping.MongoId;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "student")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

	@jakarta.persistence.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long studentId;

	@MongoId
	private String mongoId;

	private String firstName;
	private String lastName;
	private String middleName;
	private String dateOfBirth;
	private String gender;
	private String className;
	private String section;
	private Integer rollNumber;
	private String contactNumber;
	private String email;
	private String address;
	private Boolean vaccinated = false;

	@OneToOne
	@JoinColumn(name = "vaccination_record_id")
	private VaccinationRecord vaccinationRecord;
}
