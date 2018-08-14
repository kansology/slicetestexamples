package com.slicetest.repo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by tarunkansal on 12/12/17.
 */
@RunWith(SpringRunner.class)
@DataMongoTest
public class BabyProductsRepoTest {

    @Autowired
    BabyProductsRepo repo;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void test_findByCompanyName_returnList() {
        BabyProductsDoc objectToSave = new BabyProductsDoc().builder()
                .companyName("gerber")
                .productName("baby formula")
                .type("food")
                .build();
        mongoTemplate.save(objectToSave, "babyProducts");

        List<BabyProductsDoc> result = repo.findCompanyByRegexp("ger");
        assertEquals(objectToSave.getProductName(), result.get(0).getProductName());
        assertEquals(objectToSave.getType(), result.get(0).getType());
    }

    @Test
    public void test_findByCompanyName_returnEmptyList_whenCompanyNotFound() throws Exception {
        List<BabyProductsDoc> result = repo.findByCompanyName("coors");
        assertEquals(0, result.size());
    }
}
