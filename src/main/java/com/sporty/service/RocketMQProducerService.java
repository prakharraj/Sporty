package com.sporty.service;

import com.sporty.dto.Bet;
import lombok.RequiredArgsConstructor;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RocketMQProducerService {

    private final RocketMQTemplate rocketMQTemplate;

    @Value("${rocketmq.producer.group}")
    private String producerGroup;

    public void sendEventOutcome(List<Bet> bets) {
        String topic = "bet-settlements";
        rocketMQTemplate.send(topic, MessageBuilder.withPayload(bets).build());
        System.out.println("RocketMQ: Event bets sent to topic: " + topic);
    }
}