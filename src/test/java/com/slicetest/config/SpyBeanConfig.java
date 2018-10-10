package com.slicetest.config;

import com.slicetest.repo.BabyProductsRepo;
import org.mockito.AdditionalAnswers;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Profile("spy-bean-test")
@Configuration
public class SpyBeanConfig {

    @Bean
    @Primary
    public BabyProductsRepo babyProductsRepoSpy(BabyProductsRepo babyProductsRepo) {
        return Mockito.mock(BabyProductsRepo.class, AdditionalAnswers.delegatesTo(babyProductsRepo));
    }
}
