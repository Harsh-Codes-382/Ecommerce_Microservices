package com.ecommerce.Order.kafka;

import com.ecommerce.Order.utils.KafkaTopicsName;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import static com.ecommerce.Order.utils.KafkaTopicsName.ORDER_TOPIC_NAME;
import static org.springframework.kafka.support.KafkaHeaders.TOPIC;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderProducer {

    private final KafkaTemplate<String, OrderConfirmationParams> kafkaTemplate;

    public void orderConfirmationSent(OrderConfirmationParams orderArg){
        log.info("Sending Order Confirmation Email: {}", orderArg);
        Message<OrderConfirmationParams> message = MessageBuilder
                .withPayload(orderArg)
                .setHeader(TOPIC, ORDER_TOPIC_NAME)
                .build();
        kafkaTemplate.send(message);
    }
}
