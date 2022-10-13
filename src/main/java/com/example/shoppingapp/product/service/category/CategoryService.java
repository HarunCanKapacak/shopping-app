package com.example.shoppingapp.product.service.category;

import com.example.shoppingapp.product.domain.category.Category;
import com.example.shoppingapp.product.model.category.CategoryResponse;
import com.example.shoppingapp.product.model.category.CategorySaveRequest;
import com.example.shoppingapp.product.repository.mongo.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Flux<CategoryResponse> getAll(){
        return categoryRepository.findAll().map(this::mapToDto);
    }

    public Category getById(String id){
        return   categoryRepository.findById(id).block();
    }
    public CategoryResponse save(CategorySaveRequest category) {
      Category cat =  Category.builder().code("C" + category.getName().charAt(0))
                .name(category.getName())
                .build();
      return mapToDto(categoryRepository.save(cat).block());
    }
    private CategoryResponse mapToDto(Category category){
        if (category == null)
            return null;

        return CategoryResponse.builder().id(category.getId()).name(category.getName()).build();
    }


}
