package com.schoolvaccination.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "vaccination_schedule")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VaccinationSchedule {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long scheduleId;

	private LocalDate date;
	private LocalTime timeStart;
	private LocalTime timeEnd;
	private Integer slotsAvailable;

	@ManyToOne
	@JoinColumn(name = "vaccination_center_id")
	private VaccinationCenter vaccinationCenter;

	@ManyToOne
	@JoinColumn(name = "vaccine_id")
	private VaccineInfo vaccineInfo;
}
