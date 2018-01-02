package com.slicetest.service;

import com.slicetest.model.Products;
import com.slicetest.repo.BabyProductsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by tarunkansal on 12/10/17.
 */
@Service
public class BabyProductsService {

    @Autowired
    private BabyProductsRepo repo;

    @Cacheable("babyProductsByCompany")
    public List<Products> getProducts(String companyName) {
        return repo.findByCompanyName(companyName).stream()
                .map(babyProductsEntity ->
                        Products.builder().companyName(babyProductsEntity.getCompanyName())
                                .productName(babyProductsEntity.getProductName())
                                .type(babyProductsEntity.getType()).build()).collect(Collectors.toList());
    }
}
