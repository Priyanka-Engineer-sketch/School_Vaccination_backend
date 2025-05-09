package com.vaccination.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.schoolvaccination.entity.FrontLiner;

@Repository
public interface FrontLinerMongoRepository extends MongoRepository<FrontLiner, String> {

}
