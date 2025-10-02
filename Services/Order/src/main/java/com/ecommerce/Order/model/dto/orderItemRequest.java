package com.ecommerce.Order.model.dto;

public record orderItemRequest(
        Integer orderId,
        Integer productId,
        double quantity
) {
}
