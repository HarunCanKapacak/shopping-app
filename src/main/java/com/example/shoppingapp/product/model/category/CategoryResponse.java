package com.example.shoppingapp.product.model.category;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CategoryResponse {
    private String id;
    private String name;
}
