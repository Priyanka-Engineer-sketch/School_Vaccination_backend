package com.schoolvaccination.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "registration")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Registration {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long registrationId;

	private String firstName;
	private String lastName;
	private String middleName;
	private String contact;
	private String emailAddress;
	private LocalDate dateOfBirth;
	private String username;
	private String password;
	private String status; // pending, approved, disapproved
}
