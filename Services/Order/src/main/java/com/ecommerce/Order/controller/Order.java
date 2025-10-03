package com.ecommerce.Order.controller;

import com.ecommerce.Order.Service.orderService;
import com.ecommerce.Order.model.dto.OrderRequest;
import com.ecommerce.Order.model.dto.OrderResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/all/{customerId}")
    public ResponseEntity<List<OrderResponse>> getAllOrders(@PathVariable("customerId") String customerId){
        List<OrderResponse> orderResponse = orderService.getAllOrders(customerId);

        return ResponseEntity.ok(orderResponse);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> findOrder(@PathVariable("orderId") Integer orderId){
        return ResponseEntity.ok(orderService.findOrderById(orderId));
    }
}
