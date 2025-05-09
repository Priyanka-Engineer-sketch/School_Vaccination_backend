package com.vaccination.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.schoolvaccination.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
	List<Student> findByClassName(String className);

	List<Student> findByVaccinated(Boolean vaccinated);

	List<Student> findByFirstNameContainingIgnoreCase(String firstName);

	long countByVaccinatedTrue();
}
