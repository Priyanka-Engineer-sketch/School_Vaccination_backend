package com.vaccination.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.schoolvaccination.entity.FrontLinerCategory;

@Repository
public interface FrontLinerCategoryMongoRepository extends MongoRepository<FrontLinerCategory, String> {

}
