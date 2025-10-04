package com.ecommerce.Payment.mapper;

import com.ecommerce.Payment.model.dto.PaymentRequest;
import com.ecommerce.Payment.model.entity.Payment;
import org.springframework.stereotype.Service;

@Service
public class PaymentMapper {

    public Payment fromPaymentRequestToPayment(PaymentRequest req){
        if(req == null) return null;

        return Payment.builder()
                .paymentMethod(req.paymentMethod())
                .amount(req.amount())
                .orderId(req.orderId())
                .build();
    }
}
