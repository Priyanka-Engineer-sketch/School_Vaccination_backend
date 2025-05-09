package com.vaccination.repository.mongo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.schoolvaccination.entity.VaccinationSchedule;

@Repository
public interface VaccinationScheduleMongoRepository extends MongoRepository<VaccinationSchedule, String> {

	List<VaccinationSchedule> findByScheduledDateBetween(LocalDate start, LocalDate end);

}
