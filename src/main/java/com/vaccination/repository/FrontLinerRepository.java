package com.vaccination.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.schoolvaccination.entity.FrontLiner;

@Repository
public interface FrontLinerRepository extends JpaRepository<FrontLiner, Long> {

}
