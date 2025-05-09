package com.vaccination.repository.mongo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.schoolvaccination.entity.Student;

@Repository
public interface StudentMongoRepository extends MongoRepository<Student, String> {
	List<Student> findByClassName(String className);

	List<Student> findByVaccinated(Boolean vaccinated);

	List<Student> findByFirstNameContainingIgnoreCase(String firstName);

	long countByVaccinatedTrue();
}
