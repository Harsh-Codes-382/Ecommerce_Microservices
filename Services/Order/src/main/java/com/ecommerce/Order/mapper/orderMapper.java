package com.ecommerce.Order.mapper;

import com.ecommerce.Order.model.dto.OrderRequest;
import com.ecommerce.Order.model.dto.OrderResponse;
import com.ecommerce.Order.model.entity.Order;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class orderMapper {

    public Order toOrder(OrderRequest req, BigDecimal total){
        return Order.builder()
                .reference(req.reference())
                .paymentMethod(req.paymentMethod())
                .totalAmount(total)
                .build();
    }

    public OrderResponse fromOrderToOrderResponse(Order order){
        return new OrderResponse(
            order.getId(),
            order.getReference(),
            order.getTotalAmount(),
            order.getPaymentMethod(),
            order.getCustomerId()
        );
    }
}
