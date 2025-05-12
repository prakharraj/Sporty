package com.sporty.service;

import com.sporty.dto.Bet;
import com.sporty.dto.SportsEventOutcome;
import com.sporty.repository.BetRepository;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BetSettlementServiceImplTest {

    @Mock
    BetRepository betRepository;
    @Mock
    RocketMQProducerService rocketMQProducerService;
    @InjectMocks
    BetSettlementServiceImpl betSettlementService;

    @Test
    void testSettleBet_WithMatchingBets() {
        SportsEventOutcome outcome = SportsEventOutcome.builder()
                .eventId(1L)
                .eventName("Event Name")
                .build();
        List<Bet> bets = List.of(Bet.builder().betId(2L).eventId(1L).build());

        when(betRepository.findByEventId(outcome.eventId())).thenReturn(bets);

        List<Bet> result = betSettlementService.settleBet(outcome);

        assertEquals(1, result.size());
        verify(betRepository).findByEventId(outcome.eventId());
        //verify(rocketMQProducerService).sendEventOutcome(bets);
    }

    @Test
    void testSettleBet_NoMatchingBets() {
        SportsEventOutcome outcome = SportsEventOutcome.builder()
                .eventId(1L)
                .eventName("Event Name")
                .build();

        when(betRepository.findByEventId(outcome.eventId())).thenReturn(List.of());

        List<Bet> result = betSettlementService.settleBet(outcome);

        assertTrue(result.isEmpty());
        verify(betRepository).findByEventId(outcome.eventId());
        //verifyNoInteractions(rocketMQProducerService);
    }
}