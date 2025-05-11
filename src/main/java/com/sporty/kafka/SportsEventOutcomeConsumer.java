package com.sporty.kafka;

import com.sporty.dto.Bet;
import com.sporty.dto.SportsEventOutcome;
import com.sporty.service.BetSettlementService;
import com.sporty.service.RocketMQProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SportsEventOutcomeConsumer {

    private final BetSettlementService betSettlementService;
    protected final RocketMQProducerService rocketMQProducerService;

    @KafkaListener(topics = "event-outcomes", groupId = "sporty-group", containerFactory = "kafkaListenerContainerFactory")
    public void consume(SportsEventOutcome outcome) {
        System.out.println("Received event outcome: " + outcome.getEventId() + " - " + outcome.getEventName());

        List<Bet> bets = betSettlementService.settleBet(outcome);
        rocketMQProducerService.sendEventOutcome(bets);
        // Add your business logic here
    }
}