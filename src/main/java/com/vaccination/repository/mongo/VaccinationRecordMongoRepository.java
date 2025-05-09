package com.vaccination.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.schoolvaccination.entity.VaccinationRecord;

@Repository
public interface VaccinationRecordMongoRepository extends MongoRepository<VaccinationRecord, String> {

}
