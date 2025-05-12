package com.sporty.controller;

import com.sporty.dto.SportsEventOutcome;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SportsEventControllerTest {

    @Mock
    KafkaTemplate<String, SportsEventOutcome> kafkaTemplate;

    @InjectMocks
    SportsEventController sportsEventController;


    @Test
    void testPublishEventOutcome() {
        SportsEventOutcome outcome = SportsEventOutcome.builder()
                .eventId(1L)
                .eventName("Event Name")
                .build();
        when(kafkaTemplate.send(anyString(), eq(outcome))).thenReturn(null);
        String response = sportsEventController.publishEventOutcome(outcome);

        verify(kafkaTemplate, times(1)).send("event-outcomes", outcome);
        assert response.equals("Event outcome published to Kafka");
    }
}