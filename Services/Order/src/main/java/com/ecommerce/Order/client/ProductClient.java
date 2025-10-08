package com.ecommerce.Order.client;

import com.ecommerce.Order.model.dto.ProductPurchaseResponse;
import com.ecommerce.Order.model.dto.ProductToPurchase;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@FeignClient(
        // Application name of service to which we want to connect
        name = "product-service",
        path = "/api/v1/product"
)
public interface ProductClient {

    @PostMapping("/purchase")
    List<ProductPurchaseResponse> fetchProducts(@RequestBody List<ProductToPurchase> products);
}
