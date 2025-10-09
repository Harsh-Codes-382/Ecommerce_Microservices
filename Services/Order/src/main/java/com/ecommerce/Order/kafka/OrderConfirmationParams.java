package com.ecommerce.Order.kafka;

import com.ecommerce.Order.model.dto.CustomerResponse;
import com.ecommerce.Order.model.dto.ProductPurchaseResponse;
import com.ecommerce.Order.model.entity.PaymentMethod;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmationParams(
   String orderReference,
   BigDecimal totalAmount,
   PaymentMethod paymentMethod,
   CustomerResponse customer,
    List<ProductPurchaseResponse> products
) {}
