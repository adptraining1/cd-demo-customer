package com.innoq.mploed.ddd.customer.domainevents;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisEventPublisher implements EventPublisher {
    private RedisTemplate<String, String> redisTemplate;

    public RedisEventPublisher(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void publishEvent(String topic, DomainEvent event) {
        redisTemplate.convertAndSend(topic, event);
    }
}
