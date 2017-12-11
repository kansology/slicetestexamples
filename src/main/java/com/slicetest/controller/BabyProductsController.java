package com.slicetest.controller;

import com.slicetest.model.Products;
import com.slicetest.service.BabyProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by tarunkansal on 12/10/17.
 */
@RestController
public class BabyProductsController {

    private BabyProductsService productsService;

    @Autowired
    public BabyProductsController(BabyProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping("/babyproducts/{companyName}")
    public List<Products> getProducts(@PathVariable String companyName) {
        return productsService.getProducts(companyName);
    }
}
