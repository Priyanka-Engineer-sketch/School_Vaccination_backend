package com.schoolvaccination.entity;

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
@Table(name = "vaccine_info")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VaccineInfo {

	@jakarta.persistence.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long vaccineId;

	@MongoId
	private String mongoId;

	private String vaccineName;
	private String vaccineInfo;
}
