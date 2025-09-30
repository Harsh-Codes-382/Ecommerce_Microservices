package com.ecomerce.Product.service;


import com.ecomerce.Product.dto.ProductPurchaseRequest;
import com.ecomerce.Product.dto.ProductPurchaseResponse;
import com.ecomerce.Product.dto.ProductResponse;
import com.ecomerce.Product.dto.ProductRequest;
import com.ecomerce.Product.entity.Product;
import com.ecomerce.Product.exception.ProductNotFoundException;
import com.ecomerce.Product.mapper.productMapper;
import com.ecomerce.Product.repository.productRepo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class productService {
    private final productMapper productMapper;
    private final productRepo productRepo;

    public Integer create(@Valid ProductRequest req) {
        Product p = productMapper.toProduct(req);
        return productRepo.save(p).getId();
    }

    public List<ProductPurchaseResponse> purchaseProduct(List<ProductPurchaseRequest> req) {
        return null;
    }

    public ProductResponse getProductById(Integer productId) {
        return productRepo.findById(productId).map(productMapper::toProductResponse)
                .orElseThrow(
                        () -> new ProductNotFoundException(format("Product is not found with id: %d", productId))
                );
    }

    public List<ProductResponse> getAllProducts() {
        return null;
    }

    public List<ProductResponse> fetchCategoryBasedProducts() {
        return null;
    }
}
