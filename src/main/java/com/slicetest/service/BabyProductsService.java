package com.slicetest.service;

import com.slicetest.model.Products;
import com.slicetest.repo.BabyProductsEntity;
import com.slicetest.repo.BabyProductsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BabyProductsService {

    @Autowired
    private BabyProductsRepo repo;

    @Cacheable("babyProductsByCompany")
    public List<Products> getProducts(String companyName) {
        List<BabyProductsEntity> entities = repo.findByCompanyName(companyName);

        if(entities == null || entities.isEmpty()) {
            throw new EntityNotFoundException(companyName + "not available.");
        }

        return entities.stream()
                .map(babyProductsEntity ->
                        Products.builder().companyName(babyProductsEntity.getCompanyName())
                                .productName(babyProductsEntity.getProductName())
                                .type(babyProductsEntity.getType()).build()).collect(Collectors.toList());
    }
}
