package com.vaccination.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.schoolvaccination.entity.FrontLinerCategory;

@Repository
public interface FrontLinerCategoryRepository extends JpaRepository<FrontLinerCategory, Long> {

}
