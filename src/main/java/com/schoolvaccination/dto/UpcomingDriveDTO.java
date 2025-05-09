package com.schoolvaccination.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpcomingDriveDTO {

	private LocalDate date;
	private LocalTime timeStart;
	private LocalTime timeEnd;
	private String centerName;

}
