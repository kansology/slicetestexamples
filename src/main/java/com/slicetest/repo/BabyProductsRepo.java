package com.slicetest.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by tarunkansal on 12/12/17.
 */
public interface BabyProductsRepo extends CrudRepository<BabyProductsEntity, Long> {
    List<BabyProductsEntity> findByCompanyName(String companyName);

    @Query(nativeQuery = true, value = "SELECT p.id, p.company_name, p.product_name, " +
            "CASE WHEN p.type IS NULL THEN 'no type' ELSE p.type END AS type " +
            "FROM baby_products p " +
            "WHERE p.company_name = :companyName")
    List<BabyProductsEntity> findByCompanyNameWithType(@Param("companyName") String companyName);
}
