package com.ecomerce.Product.controller;

import com.ecomerce.Product.model.dto.ProductPurchaseRequest;
import com.ecomerce.Product.model.dto.ProductPurchaseResponse;
import com.ecomerce.Product.model.dto.ProductResponse;
import com.ecomerce.Product.model.dto.ProductRequest;
import com.ecomerce.Product.service.productService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class productController {

    private final productService productService;

    @PostMapping("/create")
    public ResponseEntity<Integer> createProduct(
            @RequestBody @Valid ProductRequest req
    ){
            return ResponseEntity.ok(productService.create(req));
    }

    @PostMapping("/purchase")
    public ResponseEntity<List<ProductPurchaseResponse>> productPurchase(
            @RequestBody List<ProductPurchaseRequest> req
    ){
            return ResponseEntity.ok(productService.purchaseProduct(req));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponse> getProduct(
            @PathVariable("productId") Integer productId
    ){
        return ResponseEntity.ok(productService.getProductById(productId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductResponse>> getAllProducts(){
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/category/{catId}")
    public ResponseEntity<List<ProductResponse>> getAllCategoryBasedProducts(
            @PathVariable("catId") Integer catId
    ){
        return ResponseEntity.ok(productService.fetchCategoryBasedProducts(catId));
    }
}
