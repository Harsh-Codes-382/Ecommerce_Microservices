package com.ecomerce.Product.service;


import com.ecomerce.Product.dto.*;
import com.ecomerce.Product.entity.Product;
import com.ecomerce.Product.exception.ProductPurchaseException;
import com.ecomerce.Product.mapper.productMapper;
import com.ecomerce.Product.repository.productRepo;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Transactional(rollbackFor = ProductPurchaseException.class)
    public List<ProductPurchaseResponse> purchaseProduct(List<ProductPurchaseRequest> req) {
        // Gather all the requested Product Ids
        List<Integer> productIds = req.stream()
                .map(ProductPurchaseRequest::productId)
                .toList();

        List<productQuantityDTO> storedProducts = productRepo.fetchProducts(productIds);

        if(storedProducts.size() != productIds.size()){
            throw new ProductPurchaseException(format("Some Projects are missing!"));
        }

        Map<Integer, Double> Id_qty =   req.stream()
                .collect(Collectors.toMap(ProductPurchaseRequest::productId, ProductPurchaseRequest::quantity));

        List<ProductPurchaseResponse> ans = new ArrayList<>();

        for(productQuantityDTO prod : storedProducts){
            double remain_Qty = prod.availableQuantity() - Id_qty.get(prod.id());

            if(remain_Qty < 0) throw new ProductPurchaseException("Insufficient quantity for Product: " + prod.id());

            productRepo.updateQuantity(remain_Qty, prod.id());
            ans.add(productMapper.toproductPurchaseResponse(prod, remain_Qty));
        }

        return ans;
    }

    public ProductResponse getProductById(Integer productId) {
        return productRepo.findById(productId).map(productMapper::toProductResponse)
                .orElseThrow(
                        () -> new EntityNotFoundException(format("Product is not found with id: %d", productId))
                );
    }

    public List<ProductResponse> getAllProducts() {
        return productRepo.findAll()
                .stream()
                .map(productMapper::toProductResponse)
                .collect(Collectors.toList());

    }

    public List<ProductResponse> fetchCategoryBasedProducts(
            @Positive
            Integer catId
    ) {

        return productRepo.fetchPrductsBasedOnCategory(catId)
                .stream()
                .map(productMapper::fromProductResponse)
                .toList();
    }
}
