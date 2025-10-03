package com.ecommerce.Order.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import static org.springframework.kafka.support.KafkaHeaders.TOPIC;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderProducer {

    private final KafkaTemplate<String, OrderConfirmationParams> kafkaTemplate;

    public void orderConfirmationSent(OrderConfirmationParams orderArg){
        log.info("Sending Order Confirmation Email");
        Message<OrderConfirmationParams> message = MessageBuilder
                .withPayload(orderArg)
                .setHeader(TOPIC, "Order-confirm")
                .build();
        kafkaTemplate.send(message);
    }
}
