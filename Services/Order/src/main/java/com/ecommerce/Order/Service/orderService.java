package com.ecommerce.Order.Service;

import com.ecommerce.Order.client.CustomerClient;
import com.ecommerce.Order.client.PaymentClient;
import com.ecommerce.Order.client.ProductClient;
import com.ecommerce.Order.exception.BusinessException;
import com.ecommerce.Order.kafka.OrderConfirmationParams;
import com.ecommerce.Order.kafka.OrderProducer;
import com.ecommerce.Order.mapper.orderMapper;
import com.ecommerce.Order.model.dto.*;
import com.ecommerce.Order.model.entity.Order;
import com.ecommerce.Order.model.entity.OrderItem;
import com.ecommerce.Order.repository.orderRepo;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
@Slf4j
public class orderService {

    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private  final PaymentClient paymentClient;
    private final orderRepo orderRepo;
    private final orderMapper orderMapper;
    private final OrderProducer orderProducer;

    @Transactional
    public Integer createOrder(@Valid OrderRequest req) {

        //  Check the customer -> call Customer Microservice
        log.info("Calling Customer service...");
        CustomerResponse customerResponse =   customerClient.findCustomerById(req.customerId())
                .orElseThrow(() -> new BusinessException(format("Customer Not found SO, no Order can created for id: %s", req.customerId())));
        log.info("Received Customer response: {}", customerResponse);

        // Check the Product & Update its inventory -> call Product Microservice
        log.info("Calling Products service...");
        List<ProductPurchaseResponse> products = productClient.fetchProducts(req.products());
        log.info("Received Products Service response: {}", products);

        BigDecimal total = products.stream()
                .map(ProductPurchaseResponse::price)
                .reduce(BigDecimal.ZERO, BigDecimal::add);


        // Create the Order & its Items
        Order Order = orderMapper.toOrder(req, total);

        log.info("Creating Order Items");
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
        // Since orderItems are cascade ALL to Orders so they will persist with Order
        log.info("Persisting Order");
        Order.setOrderItems(orderItems);

        Order savedOrder = orderRepo.save(Order);

        // Start Payment Process -> call Payment Microservice

        PaymentRequest paymentRequest = new PaymentRequest(
                savedOrder.getTotalAmount(),
                savedOrder.getPaymentMethod(),
                savedOrder.getId(),
                savedOrder.getReference(),
                customerResponse
        );

        log.info("Calling Payment Service");
        Integer paymentId = paymentClient.RequestOrder_Payment(paymentRequest);
        log.info("Got Payment Service Response {}", paymentId);


        // Send the Notification using (Kafka message broker) -> Notification Microservice
        log.info("Order Confirm sent to Kafka");
        orderProducer.orderConfirmationSent(
                new OrderConfirmationParams(
                        savedOrder.getReference(),
                        savedOrder.getTotalAmount(),
                        savedOrder.getPaymentMethod(),
                        customerResponse,
                        products
                )
        );

        log.info("After Order Confirm sent to Kafka");


        return savedOrder.getId();
    }

    public List<OrderResponse> getAllOrders(String customerId) {
        return orderRepo
                .findAllByCustomerId(customerId)
                .stream()
                .map(orderMapper::fromOrderToOrderResponse)
                .toList();
    }

    public OrderResponse findOrderById(Integer orderId) {
        return orderRepo
                .findById(orderId)
                .map(orderMapper::fromOrderToOrderResponse)
                .orElseThrow(() -> new EntityNotFoundException((format("Order not found for Id: %d", orderId))));
    }
}
