package com.schoolvaccination.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "vaccination_center")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VaccinationCenter {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long centerId;

	private String vaccinationCenter;
	private String address;
	private String contactNumber;
	private Double longitude;
	private Double latitude;
	private String status; // open, closed
}
