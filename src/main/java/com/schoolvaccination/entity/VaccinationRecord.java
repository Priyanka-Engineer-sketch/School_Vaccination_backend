package com.schoolvaccination.entity;

import java.time.LocalDate;

import org.springframework.data.mongodb.core.mapping.MongoId;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "vaccination_record")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VaccinationRecord {

	@jakarta.persistence.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long recordId;

	@MongoId
	private String mongoId;

	@ManyToOne
	@JoinColumn(name = "registration_id")
	private Registration registration;

	@ManyToOne
	@JoinColumn(name = "vaccine_id")
	private VaccineInfo vaccineInfo;

	private LocalDate dateOf1Dose;
	private LocalDate dateOf2Dose;
	private LocalDate dateBoosterShot;
	private String vaccineBooster;

	@ManyToOne
	@JoinColumn(name = "front_liner_id")
	private FrontLiner frontLiner;

	private String doctorComments;
	private String feelingBeforeVaccination;
	private String remarks;
}
