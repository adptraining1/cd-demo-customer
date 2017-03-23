package com.innoq.mploed.ddd.customer.domainevents;

public interface EventPublisher {
    void publishEvent(String topic, DomainEvent event);
}
