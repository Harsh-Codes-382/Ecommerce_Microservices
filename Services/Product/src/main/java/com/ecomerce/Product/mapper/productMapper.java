package com.ecomerce.Product.mapper;

import com.ecomerce.Product.dto.ProductRequest;
import com.ecomerce.Product.dto.ProductResponse;
import com.ecomerce.Product.entity.Category;
import com.ecomerce.Product.entity.Product;
import org.springframework.stereotype.Service;

@Service
public class productMapper {
    public Product toProduct(ProductRequest req){
        if(req == null) return null;

            return Product.builder()
                    .id(req.id())
                    .price(req.price())
                    .name(req.name())
                    .availableQuantity(req.availableQuantity())
                    .description(req.description())
                    .category(
                            Category.builder()
                                    .id(req.categoryId())
                                    .build()
                    )
                    .build();
    }

    public ProductResponse toProductResponse(Product product) {

        ProductResponse.Category category = null;
        if(product.getCategory() != null){
            category = new ProductResponse.Category(
                    product.getCategory().getId(),
                    product.getCategory().getName(),
                    product.getCategory().getDescription()
            );
        }
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getAvailableQuantity(),
                product.getPrice(),
                category
        );
    }
}
