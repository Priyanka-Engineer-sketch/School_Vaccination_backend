package com.schoolvaccination.entity;

import java.time.LocalDate;

import org.springframework.data.mongodb.core.mapping.MongoId;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

	@jakarta.persistence.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long registrationId;

	@MongoId
	private String mongoId;

	private String firstName;
	private String lastName;
	private String middleName;
	private String contact;
	private String emailAddress;
	private String dateOfBirth;
	private String username;
	private String password;
	private String status; // pending, approved, disapproved
}
