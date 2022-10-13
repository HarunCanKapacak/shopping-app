package com.example.shoppingapp.product.service;

import com.example.shoppingapp.product.domain.MoneyTypes;
import com.example.shoppingapp.product.domain.Product;
import com.example.shoppingapp.product.domain.ProductImage;
import com.example.shoppingapp.product.domain.es.ProductEs;
import com.example.shoppingapp.product.model.ProductDetailResponse;
import com.example.shoppingapp.product.model.ProductResponse;
import com.example.shoppingapp.product.model.ProductSaveRequest;
import com.example.shoppingapp.product.model.ProductSellerResponse;
import com.example.shoppingapp.product.repository.mongo.ProductRepository;
import com.example.shoppingapp.product.repository.es.ProductEsRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductEsService productEsService;
    private final ProductRepository productRepository;
    private final ProductDeliveryService productDeliveryService;
    private final ProductAmountService productAmountService;


    public ProductResponse saveProduct(ProductSaveRequest productSaveRequest){
         Product product = Product.builder()
                 .active(Boolean.TRUE)
                .features(productSaveRequest.getFeaterues())
                .id(productSaveRequest.getId())
                .code("Pr0001")
                .companyId(productSaveRequest.getSellerId())
                .name(productSaveRequest.getName())
                .categoryId(productSaveRequest.getCategoryId())
                .description(productSaveRequest.getDescription())
                 .price(productSaveRequest.getPrice())
                .productImage(productSaveRequest.getImages().stream().map(it -> new ProductImage(ProductImage.ImageType.FEATURE,it)).collect(Collectors.toList()))
                .build();
         product = productRepository.save(product).block();



        return this.mapToDto(productEsService.saveProduct(product).block());
    }

   public  Flux<ProductResponse> getAll(){
        return productEsService.findAll().map(this::mapToDto);
    }

    private ProductResponse mapToDto(ProductEs item) {

        if (item == null) {
            return null;
        }

       return ProductResponse.builder()
               .features(item.getFeatures())
               .price(item.getPrice().get(MoneyTypes.USD))
               .moneySymbol(MoneyTypes.USD.getSymbol())
               .name(item.getName())
               .id(item.getId())
               .description(item.getDescription())
               .deliveryIn(productDeliveryService.getDeliveryInfo(item.getId()))
               .categoryId(item.getCategory().getId())
               .available(productAmountService.getByProductId(item.getId()))
               .freeDelivery(productDeliveryService.freeDeliveryCheck(item.getId(),item.getPrice().get("USD"),MoneyTypes.USD ))
               .image(item.getImages().get(0))
               .seller(ProductSellerResponse.builder().id(item.getSeller().getId()).name(item.getSeller().getName()).build())
               .build();
    }

    public Mono<Long> count() {
        return productRepository.count();
    }

    public Mono<ProductDetailResponse> getProductDetail(String id) {
        return this.mapToDto(productEsService.findById(id));
    }
    private Mono<ProductDetailResponse> mapToDto(Mono<ProductEs> product) {
        return product.map( item -> ProductDetailResponse.builder()
                .price(item.getPrice().get("USD"))
                .moneySymbol(MoneyTypes.USD.getSymbol())
                .name(item.getName())
                .features(item.getFeatures())
                .id(item.getId())
                .description(item.getDescription())
                .deliveryIn(productDeliveryService.getDeliveryInfo(item.getId()))
                .categoryId(item.getCategory().getId())
                .available(productAmountService.getByProductId(item.getId()))
                .freeDelivery(productDeliveryService.freeDeliveryCheck(item.getId(), item.getPrice().get("USD"), MoneyTypes.USD))
                .images(item.getImages())
                .seller(ProductSellerResponse.builder().id(item.getSeller().getId()).name(item.getSeller().getName()).build())
                .build());
    }
}
