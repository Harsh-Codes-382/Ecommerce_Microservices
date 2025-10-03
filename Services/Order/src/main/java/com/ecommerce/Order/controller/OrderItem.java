package com.ecommerce.Order.controller;

import com.ecommerce.Order.Service.orderItemService;
import com.ecommerce.Order.model.dto.OrderItemResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order-item")
@RequiredArgsConstructor
public class OrderItem {

    private final orderItemService orderItemService;

    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<OrderItemResponse>> getAllOrderItems(@PathVariable("orderId") Integer orderId){
        return ResponseEntity.ok(orderItemService.findItemsByOrderId(orderId));
    }
}
