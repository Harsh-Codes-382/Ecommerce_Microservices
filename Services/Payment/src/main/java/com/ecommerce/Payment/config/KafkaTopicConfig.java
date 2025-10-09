package com.ecommerce.Payment.config;

import com.ecommerce.Payment.utils.KafkaTopicName;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import static com.ecommerce.Payment.utils.KafkaTopicName.ORDER_TOPIC_NAME;

@Configuration
public class KafkaTopicConfig {
    @Bean
    public NewTopic orderPaymentEvent(){
        return TopicBuilder
                .name(ORDER_TOPIC_NAME)
                .build();
    }
}
