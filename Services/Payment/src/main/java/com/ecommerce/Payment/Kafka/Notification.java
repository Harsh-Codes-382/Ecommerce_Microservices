package com.ecommerce.Payment.Kafka;


import com.ecommerce.Payment.Kafka.Producer.PaymentNotificationRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import static com.ecommerce.Payment.utils.KafkaTopicName.ORDER_TOPIC_NAME;
import static org.springframework.kafka.support.KafkaHeaders.TOPIC;

@Service
@Slf4j
@RequiredArgsConstructor
public class Notification {
    private final KafkaTemplate<String, PaymentNotificationRequest> kafkaTemplate;

    public void SendNotification(PaymentNotificationRequest req){
        log.info("Payment Notification is processing body Sent = {}", req);
        Message<PaymentNotificationRequest> message = MessageBuilder
                .withPayload(req)
                .setHeader(TOPIC, ORDER_TOPIC_NAME)
                .build();

        kafkaTemplate.send(message);
    }
}
