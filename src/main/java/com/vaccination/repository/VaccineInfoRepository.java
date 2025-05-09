package com.vaccination.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.schoolvaccination.entity.VaccineInfo;

@Repository
public interface VaccineInfoRepository extends JpaRepository<VaccineInfo, Long> {

}
