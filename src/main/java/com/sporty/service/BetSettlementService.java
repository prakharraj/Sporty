package com.sporty.service;

import com.sporty.dto.Bet;
import com.sporty.dto.SportsEventOutcome;

import java.util.List;

public interface BetSettlementService {
    public List<Bet> settleBet(SportsEventOutcome outcome);
}
