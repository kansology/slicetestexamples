package com.slicetest.controller;

import com.slicetest.model.Products;
import com.slicetest.service.BabyProductsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by tarunkansal on 12/10/17.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(BabyProductsController.class)
public class BabyProductsControllerTest {

    @MockBean
    BabyProductsService productsService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void test_getProducts_returnList() throws Exception {
        List<Products> gerberProducts = asList(new Products("gerber", "baby formula", "food"));
        when(productsService.getProducts("gerber")).thenReturn(gerberProducts);
        this.mockMvc.perform(get("/babyproducts/{companyName}", "gerber"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("[0].productName").value("baby formula"))
                .andExpect(jsonPath("[0].type").value("food"));
    }

    @Test
    public void test_getProducts_return404_whenCompanyNotFound() throws Exception {
        when(productsService.getProducts("coors")).thenThrow(new EntityNotFoundException("company not found"));
        this.mockMvc.perform(get("/babyproducts/{companyName}", "coors"))
                .andExpect(status().isNotFound());
    }


}
