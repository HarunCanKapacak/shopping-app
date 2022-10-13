package com.example.shoppingapp.product.domain.category;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "category")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Builder
public class Category {
    @Id
    private String id;
    private String name;
    private String code;
}
