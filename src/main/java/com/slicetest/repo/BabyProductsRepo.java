package com.slicetest.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.List;

/**
 * Created by tarunkansal on 12/12/17.
 */
@EnableMongoRepositories
public interface BabyProductsRepo extends MongoRepository<BabyProductsDoc, String> {
    List<BabyProductsDoc> findByCompanyName(String companyName);

    @Query("{ 'companyName' : { $regex: ?0 } }")
    List<BabyProductsDoc> findCompanyByRegexp(String regexp);
}
