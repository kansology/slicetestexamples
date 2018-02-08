package com.slicetest.repo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by tarunkansal on 12/12/17.
 */
@Document(collection = "babyProducts")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class BabyProductsDoc {

    @Id
    private String id;

    private String companyName;
    private String productName;
    private String type;
}
