package com.vaccination.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.schoolvaccination.entity.VaccineInfo;

@Repository
public interface VaccineInfoMongoRepository extends MongoRepository<VaccineInfo, String> {

}
