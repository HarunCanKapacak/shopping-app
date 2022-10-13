package com.example.shoppingapp.product.startup;

import com.example.shoppingapp.filestrore.service.FileStoreService;
import com.example.shoppingapp.product.domain.MoneyTypes;
import com.example.shoppingapp.product.model.ProductResponse;
import com.example.shoppingapp.product.model.ProductSaveRequest;
import com.example.shoppingapp.product.model.category.CategoryResponse;
import com.example.shoppingapp.product.model.category.CategorySaveRequest;
import com.example.shoppingapp.product.repository.es.ProductEsRepository;
import com.example.shoppingapp.product.service.ProductService;
import com.example.shoppingapp.product.service.category.CategoryService;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductDemoData {

    private final ProductService productService;
    private final ProductEsRepository productEsRepository;
    private final CategoryService categoryService;
    private final FileStoreService fileStoreService;

    @EventListener(ApplicationReadyEvent.class)
    public void migrete(){


        Long countOfData = productService.count().block();
        assert  countOfData != null;
        if(countOfData.equals(0L)){
            productEsRepository.deleteAll().block();

            categoryService.save(CategorySaveRequest.builder().name("elektronik").build());
            CategoryResponse telefon   =  categoryService.save(CategorySaveRequest.builder().name("cep telefon").build());

            IntStream.range(0,5).forEach(item -> {
                HashMap<MoneyTypes, BigDecimal> price = new HashMap<>(){{
                   put(MoneyTypes.USD,BigDecimal.valueOf(5000));
                    put(MoneyTypes.EUR,BigDecimal.valueOf(4000));
                }};
                String imgUuid = UUID.randomUUID().toString();

                byte[] file = null;
                try {
                    file = Files.readAllBytes(ResourceUtils.getFile("classpath:docs/app.jpg").toPath());
                } catch (IOException e) {
                    log.error("File read error:", e);
                }
                fileStoreService.saveImage(imgUuid, new ByteArrayInputStream(file));


               productService.saveProduct(
                ProductSaveRequest.builder()
                        .id(UUID.randomUUID().toString())
                        .sellerId(UUID.randomUUID().toString())
                        .description("Product  " + item)
                        .price(price)
                        .categoryId(telefon.getId())
                        .name("Product " + item)
                        .featerues("<li>Black Color</li> <li>Aluminum Case</li> <li>2 Years Warranty</li> <li>5 Inch (35x55mm)</li>")
                        .images(List.of(imgUuid))
                        .build());

            });
        }



    }




}
