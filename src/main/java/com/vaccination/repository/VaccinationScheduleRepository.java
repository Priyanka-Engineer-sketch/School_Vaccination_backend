package com.vaccination.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.schoolvaccination.entity.VaccinationSchedule;

@Repository
public interface VaccinationScheduleRepository extends JpaRepository<VaccinationSchedule, Long> {
	
	List<VaccinationSchedule> findByScheduledDateBetween(LocalDate start, LocalDate end);

}
