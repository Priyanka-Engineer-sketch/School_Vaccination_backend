package com.schoolvaccination.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "vaccine_info")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VaccineInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long vaccineId;

	private String vaccineName;
	private String vaccineInfo;
}
