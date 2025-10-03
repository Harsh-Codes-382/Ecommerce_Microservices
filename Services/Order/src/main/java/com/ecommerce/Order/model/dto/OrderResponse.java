package com.ecommerce.Order.model.dto;

import com.ecommerce.Order.model.entity.PaymentMethod;
import com.fasterxml.jackson.annotation.JsonInclude;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

import java.math.BigDecimal;


@JsonInclude(NON_EMPTY)
public record OrderResponse(
        Integer id,
        String reference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerId
) {}
