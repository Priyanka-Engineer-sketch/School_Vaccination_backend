package com.vaccination.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.schoolvaccination.entity.VaccinationRecord;

@Repository
public interface VaccinationRecordRepository extends JpaRepository<VaccinationRecord, Long> {

}
