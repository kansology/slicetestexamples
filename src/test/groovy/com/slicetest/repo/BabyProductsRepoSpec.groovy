package com.slicetest.repo

import com.slicetest.SlicetestexamplesApplication
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

@ContextConfiguration(classes = [SlicetestexamplesApplication.class])
@DataJpaTest
class BabyProductsRepoSpec extends Specification {

    @Autowired
    BabyProductsRepo repo

    @Autowired
    TestEntityManager entityManager

    def "test findByCompanyName returnList"() throws Exception {
        given:
        BabyProductsEntity entity = entityManager.persistAndFlush(new BabyProductsEntity().builder()
                .companyName("gerber").productName("baby formula").type("food").build())

        when:
        List<BabyProductsEntity> result = this.repo.findByCompanyName("gerber")

        then:
        entity.getProductName() == result.get(0).getProductName()
        entity.getType() == result.get(0).getType()
    }

    def "test findByCompanyName returnEmptyList whenCompanyNotFound"() throws Exception {
        when:
        List<BabyProductsEntity> result = this.repo.findByCompanyName("coors");

        then:
        0 == result.size()
    }
}
