package com.sporty.service;


import com.sporty.dto.Bet;
import com.sporty.dto.SportsEventOutcome;
import com.sporty.repository.BetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BetSettlementService {

    private final BetRepository betRepository;

    public List<Bet> settleBet(SportsEventOutcome outcome) {
        List<Bet> bets = betRepository.findByEventId(outcome.getEventId());
        System.out.println("Settling bets for event: " + outcome.getEventId() + " - " + outcome.getEventName());
        return bets;
    }

}
