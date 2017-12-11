package com.slicetest.model;

import lombok.*;

/**
 * Created by tarunkansal on 12/10/17.
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class Products {
    private final String companyName;
    private final String productName;
    private final String type;
}
