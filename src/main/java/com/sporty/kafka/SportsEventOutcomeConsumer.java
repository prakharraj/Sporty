package com.sporty.kafka;

import com.sporty.dto.Bet;
import com.sporty.dto.SportsEventOutcome;
import com.sporty.service.BetSettlementService;
import com.sporty.service.BetSettlementServiceImpl;
import com.sporty.service.RocketMQProducerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Log4j2
public class SportsEventOutcomeConsumer {

    private final BetSettlementService betSettlementService;

    @KafkaListener(topics = "event-outcomes", groupId = "sporty-group", containerFactory = "kafkaListenerContainerFactory")
    public void consume(SportsEventOutcome outcome) {
        log.info("Received event outcome: {} - {}", outcome.getEventId(), outcome.getEventName());
        List<Bet> bets = betSettlementService.settleBet(outcome);
        log.info("Settled bets: {}", bets);
    }
}