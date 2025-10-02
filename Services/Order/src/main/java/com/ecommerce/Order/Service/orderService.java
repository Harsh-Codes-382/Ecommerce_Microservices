package com.ecommerce.Order.Service;

import com.ecommerce.Order.client.CustomerClient;
import com.ecommerce.Order.client.ProductClient;
import com.ecommerce.Order.exception.BusinessException;
import com.ecommerce.Order.mapper.orderMapper;
import com.ecommerce.Order.model.dto.CustomerResponse;
import com.ecommerce.Order.model.dto.OrderRequest;
import com.ecommerce.Order.model.dto.ProductPurchaseResponse;
import com.ecommerce.Order.model.dto.orderItemRequest;
import com.ecommerce.Order.model.entity.OrderItem;
import com.ecommerce.Order.model.entity.order;
import com.ecommerce.Order.repository.orderRepo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class orderService {

    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final orderRepo orderRepo;
    private final orderMapper orderMapper;
    private final orderItemService orderItemService;

    public Integer createOrder(@Valid OrderRequest req) {

        //  Check the customer -> call Customer Microservice
        CustomerResponse customerResponse =   customerClient.findCustomerById(req.customerId())
                .orElseThrow(() -> new BusinessException(format("Customer Not found SO, no order can created for id: %s", req.customerId())));

        // Check the Product & Update its inventory -> call Product Microservice
        List<ProductPurchaseResponse> products = productClient.fetchProducts(req.products());

        BigDecimal total = products.stream()
                .map(ProductPurchaseResponse::price)
                .reduce(BigDecimal.ZERO, BigDecimal::add);


        // Create the Order & its Items
        order Order = orderMapper.toOrder(req, total);

        List<OrderItem> orderItems = products.stream()
                .map(prod ->
                       new OrderItem(
                               null,
                               Order,
                               prod.productId(),
                               prod.quantity()
                       )
                )
                .toList();
        // Since orderItems are cascade ALL to Orders so they will persist with order
        Order.setOrderItems(orderItems);

        order savedOrder = orderRepo.save(Order);

        // Start Payment Process -> call Payment Microservice

        // Send the Notification using (Kafka message broker) -> Notification Microservice

        return null;
    }
}
