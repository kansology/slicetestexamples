package com.slicetest.service

import com.slicetest.SlicetestexamplesApplication
import com.slicetest.repo.BabyProductsEntity
import com.slicetest.repo.BabyProductsRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.ContextConfiguration
import spock.lang.Ignore
import spock.lang.Specification

import javax.persistence.EntityNotFoundException

import static java.util.Arrays.asList

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ContextConfiguration(classes = [SlicetestexamplesApplication.class])
@AutoConfigureTestDatabase
@Ignore
class BabyProductsServiceSpec extends Specification {

    @Autowired
    BabyProductsService service

    @MockBean
    private final BabyProductsRepo repo

    def "getProducts returnCachedValues"() throws Exception {
        when:
        repo.findByCompanyName("gerber") >> asList(new BabyProductsEntity().builder()
                .companyName("gerber").productName("baby formula").type("food").build())
        service.getProducts("gerber")
        service.getProducts("gerber")
        service.getProducts("gerber")
        service.getProducts("gerber")

        then:
        1 * repo.findByCompanyName("gerber")
    }

    def "getProducts throwError whenCompanyNotFound"() throws Exception {
        when:
        repo.findByCompanyName("coors") >> Collections.emptyList()
        service.getProducts("coors")
        service.getProducts("coors")
        service.getProducts("coors")
        service.getProducts("coors")

        then:
        thrown(EntityNotFoundException)
        1 * repo.findByCompanyName("coors")
    }
}
