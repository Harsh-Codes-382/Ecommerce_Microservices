package com.ecommerce.Order.mapper;

import com.ecommerce.Order.model.dto.orderItemRequest;
import com.ecommerce.Order.model.entity.OrderItem;
import com.ecommerce.Order.model.entity.order;
import org.springframework.stereotype.Service;

@Service
public class orderItemMapper {

    public OrderItem toOrderItem(orderItemRequest ord) {
        return OrderItem.builder()
                .productId(ord.productId())
                .quantity(ord.quantity())
                .order(
                        order.builder()
                                .id(ord.orderId())
                                .build()
                )
                .build();
    }
}
