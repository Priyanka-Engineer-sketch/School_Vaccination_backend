package com.vaccination.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.schoolvaccination.entity.VaccinationCenter;

@Repository
public interface VaccinationCenterMongoRepository extends MongoRepository<VaccinationCenter, String> {

}
