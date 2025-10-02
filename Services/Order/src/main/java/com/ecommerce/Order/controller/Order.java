package com.ecommerce.Order.controller;

import com.ecommerce.Order.Service.orderService;
import com.ecommerce.Order.model.dto.OrderRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class Order {

    private final orderService orderService;

    @PostMapping("create")
    public ResponseEntity<Integer> createOrder(
        @RequestBody @Valid OrderRequest req
    ){
        return ResponseEntity.ok(orderService.createOrder(req));
    }
}
