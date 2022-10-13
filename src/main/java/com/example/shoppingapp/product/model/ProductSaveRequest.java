package com.example.shoppingapp.product.model;

import com.example.shoppingapp.product.domain.MoneyTypes;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

@Builder
@Data
public class ProductSaveRequest {

    private String id;
    private String name;
    private String featerues;
    private String description;
    private BigDecimal available;
    private HashMap<MoneyTypes, BigDecimal> price;
    private List<String> images;
    private String sellerId;
    private String categoryId;
}
