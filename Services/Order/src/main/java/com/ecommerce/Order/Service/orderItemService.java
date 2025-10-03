package com.ecommerce.Order.Service;

import com.ecommerce.Order.mapper.orderItemMapper;
import com.ecommerce.Order.model.dto.OrderItemResponse;
import com.ecommerce.Order.model.dto.orderItemRequest;
import com.ecommerce.Order.model.entity.OrderItem;
import com.ecommerce.Order.repository.orderItemRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class orderItemService {

    private final orderItemRepo orderItemRepo;
    private final orderItemMapper orderItemMapper;


    public Integer create(orderItemRequest ord) {
        OrderItem order = orderItemMapper.toOrderItem(ord);
        return orderItemRepo.save(order).getId();
    }

    public List<OrderItemResponse> findItemsByOrderId(Integer orderId) {
        return orderItemRepo
                .findByOrderId(orderId)
                .stream()
                .map(orderItemMapper::fromOrderItemToOrderItemResponse)
                .toList();
    }
}
