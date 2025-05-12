package com.sporty.kafka;

import com.sporty.dto.Bet;
import com.sporty.dto.SportsEventOutcome;
import com.sporty.service.BetSettlementService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SportsEventOutcomeConsumerTest {

    @Mock
    BetSettlementService betSettlementService;
    @InjectMocks
    SportsEventOutcomeConsumer sportsEventOutcomeConsumer;

    @Test
    void consume_shouldLogAndSettleBets() {
        SportsEventOutcome outcome = SportsEventOutcome.builder().eventId(1L).eventName("Event Name").build();

        List<Bet> settledBets = List.of(Bet.builder().betId(2L).eventId(1L).build(),
                Bet.builder().betId(3L).eventId(1L).build());
        when(betSettlementService.settleBet(outcome)).thenReturn(settledBets);

        sportsEventOutcomeConsumer.consume(outcome);

        verify(betSettlementService).settleBet(outcome);
    }
}