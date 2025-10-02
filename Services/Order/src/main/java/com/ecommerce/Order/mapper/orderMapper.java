package com.ecommerce.Order.mapper;

import com.ecommerce.Order.model.dto.OrderRequest;
import com.ecommerce.Order.model.entity.order;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class orderMapper {

    public order toOrder(OrderRequest req, BigDecimal total){
        return order.builder()
                .reference(req.reference())
                .paymentMethod(req.paymentMethod())
                .totalAmount(total)
                .build();
    }
}
