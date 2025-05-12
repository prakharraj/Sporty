package com.sporty.service;


import com.sporty.dto.Bet;
import com.sporty.dto.SportsEventOutcome;
import com.sporty.repository.BetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class BetSettlementServiceImpl implements BetSettlementService {

    private final BetRepository betRepository;
    private final RocketMQProducerService rocketMQProducerService;

    @Override
    public List<Bet> settleBet(SportsEventOutcome outcome) {
        List<Bet> bets = betRepository.findByEventId(outcome.eventId());

        if (CollectionUtils.isNotEmpty(bets)) {
            log.info("Got {} matching bets for event: {} - {}",  bets.size(), outcome.eventId(), outcome.eventName());
            log.info("Sending bets to rocketMQ for settlement : {}", bets);
            //rocketMQProducerService.sendEventOutcome(bets); TODO : rocketMQ setup
        }
        else{
            log.warn("No matching bets found for event: {} - {}", outcome.eventId(), outcome.eventName());
        }
        return bets;
    }

}
