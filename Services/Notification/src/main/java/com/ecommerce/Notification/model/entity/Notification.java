package com.ecommerce.Notification.model.entity;


import com.ecommerce.Notification.model.dto.Order_DTO.OrderConfirmation;
import com.ecommerce.Notification.model.dto.Payment_DTO.PaymentConfirmation;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Document
public class Notification {

    @Id
    private String Id;

    private  NotificationType notificationType;

    private LocalDateTime createdAt;

    private OrderConfirmation orderConfirmation;
    private PaymentConfirmation paymentConfirmation;
}
