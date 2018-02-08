package com.slicetest.service;

import com.slicetest.exception.CompanyNotFoundException;
import com.slicetest.model.Products;
import com.slicetest.repo.BabyProductsDoc;
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
        List<BabyProductsDoc> entities = repo.findByCompanyName(companyName);

        if (entities == null || entities.isEmpty()) {
            throw new CompanyNotFoundException(companyName + "not available.");
        }

        return entities.stream()
                .map(babyProductsDoc ->
                        Products.builder().companyName(babyProductsDoc.getCompanyName())
                                .productName(babyProductsDoc.getProductName())
                                .type(babyProductsDoc.getType()).build()).collect(Collectors.toList());
    }
}
