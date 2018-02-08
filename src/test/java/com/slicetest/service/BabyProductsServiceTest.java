package com.slicetest.service;

import com.slicetest.repo.BabyProductsDoc;
import com.slicetest.repo.BabyProductsRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.*;

/**
 * Created by tarunkansal on 1/1/18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureDataMongo
public class BabyProductsServiceTest {

    @Autowired
    BabyProductsService service;

    @MockBean
    BabyProductsRepo repo;

    @Test
    public void getProducts_returnCachedValues() throws Exception {
        when(repo.findByCompanyName("gerber")).thenReturn(asList(new BabyProductsDoc().builder()
                .companyName("gerber").productName("baby formula").type("food").build()));

        service.getProducts("gerber");
        service.getProducts("gerber");
        service.getProducts("gerber");
        service.getProducts("gerber");

        verify(repo, times(1)).findByCompanyName("gerber");

    }

    /*@Test(expected = EntityNotFoundException.class)
    public void getProducts_throwError_whenCompanyNotFound() throws Exception {
        when(repo.findByCompanyName("coors")).thenReturn(Collections.emptyList());

        service.getProducts("coors");
        service.getProducts("coors");
        service.getProducts("coors");
        service.getProducts("coors");

        verify(repo, times(1)).findByCompanyName("coors");

    }*/

}