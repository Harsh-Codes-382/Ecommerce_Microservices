package com.ecommerce.Order.kafka;

import com.ecommerce.Order.model.dto.CustomerResponse;
import com.ecommerce.Order.model.dto.ProductPurchaseResponse;
import com.ecommerce.Order.model.entity.PaymentMethod;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmationParams(
   String orderReference,
   BigDecimal total,
   PaymentMethod paymentMethod,
   CustomerResponse customerResponse,
    List<ProductPurchaseResponse> products
) {}
