package com.slicetest.controller

import com.slicetest.SlicetestexamplesApplication
import com.slicetest.model.Products
import com.slicetest.service.BabyProductsService
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import javax.persistence.EntityNotFoundException

import static java.util.Arrays.asList
import static org.mockito.Mockito.when
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(BabyProductsController.class)
@ContextConfiguration(classes = [SlicetestexamplesApplication.class])
class BabyProductsControllerSpec extends Specification {
    @MockBean
    BabyProductsService productsService
    @Autowired
    private MockMvc mockMvc

    def "test getProducts returnList"() throws Exception {
        when:
        List<Products> gerberProducts = asList(new Products("gerber", "baby formula", "food"))
        when(productsService.getProducts("gerber")).thenReturn(gerberProducts)

        then:
        this.mockMvc.perform(get("/babyproducts/{companyName}", "gerber"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("[0].productName").value("baby formula"))
                .andExpect(jsonPath("[0].type").value("food"))
    }

    def "test getProducts return404 whenCompanyNotFound"() throws Exception {
        when:
        when(productsService.getProducts("coors")).thenThrow(new EntityNotFoundException("company not found"));

        then:
        this.mockMvc.perform(get("/babyproducts/{companyName}", "coors"))
                .andExpect(status().isNotFound());
    }
}
