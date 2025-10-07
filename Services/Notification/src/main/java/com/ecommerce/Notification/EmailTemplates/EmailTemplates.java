package com.ecommerce.Notification.EmailTemplates;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum EmailTemplates {
    PAYMENT_CONFIRMATION("payment-confirm.html", "Payment Done successfully"),
    ORDER_CONFIRMATION("order-confirm.html", "Order Confirmed");

    @Getter
    private final String templateName;
    @Getter
    private final String templateSubject;


}
