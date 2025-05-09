package com.vaccination.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.schoolvaccination.entity.Registration;

@Repository
public interface RegistrationMongoRepository extends MongoRepository<Registration, String> {

}
