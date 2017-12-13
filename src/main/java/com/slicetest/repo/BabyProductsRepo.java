package com.slicetest.repo;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by tarunkansal on 12/12/17.
 */
public interface BabyProductsRepo extends CrudRepository<BabyProductsEntity, Long> {
    List<BabyProductsEntity> findByCompanyName(String companyName);
}
