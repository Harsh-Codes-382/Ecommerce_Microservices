package com.ecommerce.Order.client;

import com.ecommerce.Order.model.dto.PaymentRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        // Application name of service to which we want to connect
        name = "payment-service"
)
public interface PaymentClient {

    @PostMapping("/create")
    Integer RequestOrder_Payment(@RequestBody PaymentRequest request);

}
