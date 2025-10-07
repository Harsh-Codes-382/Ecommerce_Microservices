package com.ecommerce.Notification.kafka.Payment_consumer;

import com.ecommerce.Notification.model.dto.Payment_DTO.PaymentConfirmation;
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
public class PaymentConfirmNotificationConsumer {

    private final NotificationRepository notificationRepository;
    private final EmailService emailService;
    @KafkaListener(topics = "order-payment-confirm-topic")
    public void ConsumePaymentConfirmNotificationMessage(PaymentConfirmation payment) throws MessagingException {

        log.info("Payment Confirmation Notification Message starts processing: {}", payment);

        // 1. Save this notification into DB
        notificationRepository.save(
                Notification.builder()
                        .paymentConfirmation(payment)
                        .createdAt(LocalDateTime.now())
                        .notificationType(NotificationType.PAYMENT_CONFIRMATION)
                        .build()
        );

        // 2. Send The Email for Payment confirmation
        emailService.sendPaymentEmail(
                payment.customerEmail(),
                payment.customerFirstname() + payment.customerLastname(),
                payment.amount(),
                payment.orderReference()
        );
    }
}
