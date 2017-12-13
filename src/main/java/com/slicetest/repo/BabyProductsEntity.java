package com.slicetest.repo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by tarunkansal on 12/12/17.
 */
@Entity
@Table(name = "baby_products")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class BabyProductsEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String companyName;
    private String productName;
    private String type;
}
