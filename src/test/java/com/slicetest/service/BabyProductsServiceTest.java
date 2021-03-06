package com.slicetest.service;

import com.slicetest.repo.BabyProductsEntity;
import com.slicetest.repo.BabyProductsRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.*;

/**
 * Created by tarunkansal on 1/1/18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureTestDatabase
public class BabyProductsServiceTest {

    @Autowired
    BabyProductsService service;

    @MockBean
    BabyProductsRepo repo;

    @Test
    public void getProducts_returnCachedValues() throws Exception {
        when(repo.findByCompanyName("gerber")).thenReturn(asList(new BabyProductsEntity().builder()
                .companyName("gerber").productName("baby formula").type("food").build()));

        service.getProducts("gerber");
        service.getProducts("gerber");
        service.getProducts("gerber");
        service.getProducts("gerber");

        verify(repo, times(1)).findByCompanyName("gerber");

    }

    @Test
    public void getProducts_throwError_whenCompanyNotFound() throws Exception {
        when(repo.findByCompanyName("coors")).thenReturn(Collections.emptyList());

        try {
            service.getProducts("coors");
        } catch (EntityNotFoundException e) {
        }
        try {
            service.getProducts("coors");
        } catch (EntityNotFoundException e) {
        }
        try {
            service.getProducts("coors");
        } catch (EntityNotFoundException e) {
        }
        try {
            service.getProducts("coors");
        } catch (EntityNotFoundException e) {
        }

        verify(repo, times(4)).findByCompanyName("coors");
    }

}