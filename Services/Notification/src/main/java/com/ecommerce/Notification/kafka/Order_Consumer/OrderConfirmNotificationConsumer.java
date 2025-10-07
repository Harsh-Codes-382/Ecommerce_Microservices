package com.ecommerce.Notification.kafka.Order_Consumer;


import com.ecommerce.Notification.model.dto.Order_DTO.OrderConfirmation;
import com.ecommerce.Notification.model.entity.Notification;
import com.ecommerce.Notification.model.entity.NotificationType;
import com.ecommerce.Notification.repository.NotificationRepository;
import com.ecommerce.Notification.service.EmailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderConfirmNotificationConsumer {
    private final NotificationRepository notificationRepository;
    private final EmailService emailService;

    @KafkaListener(topics = "Order-confirm-topic")
    public void ConsumeOrderConfirmationMessage(OrderConfirmation order) throws MessagingException {
        log.info("Order Confirmation Notification starts Processing: {}", order);

        // 1. Save this notification into DB
        notificationRepository.save(
                Notification.builder()
                        .orderConfirmation(order)
                        .createdAt(LocalDateTime.now())
                        .notificationType(NotificationType.ORDER_CONFIRMATION)
                        .build()
        );

        // 2. Send The Email for Order confirmation
        emailService.sendOrderConfirmEmail(
                order.customer().email(),
                order.customer().firstname() + order.customer().lastname(),
                order.totalAmount(),
                order.orderReference(),
                order.products()
        );
    }
}
