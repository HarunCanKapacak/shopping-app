package com.example.shoppingapp.product.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

@Document(collection = "product")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Builder
public class Product {

    @Id
    private String id;
    private String name;
    private String code;
    private String description;
    private String companyId;
    private String features;
    private String categoryId;
    private List<ProductImage> productImage;
    private HashMap<MoneyTypes, BigDecimal> price;
    private Boolean active;

}
