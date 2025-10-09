package com.ecommerce.Order.config;

import com.ecommerce.Order.utils.KafkaTopicsName;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import static com.ecommerce.Order.utils.KafkaTopicsName.ORDER_TOPIC_NAME;

@Configuration
public class KafkaTopicConfig {
    @Bean
    public NewTopic orderTopic(){
        return TopicBuilder
                .name(ORDER_TOPIC_NAME)
                .build();
    }
}
