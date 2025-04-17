package com.mgh.order.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.web.client.RestTemplate;

@Configuration
public class OrderConfiguration {

    @Bean
    NewTopic orderTopic() {
		return TopicBuilder.name("order-topic").build();
	}
    
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}