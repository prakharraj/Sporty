package com.sporty.controller;

import com.sporty.dto.SportsEventOutcome;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SportsEventControllerTest {

    @Mock
    KafkaTemplate<String, SportsEventOutcome> kafkaTemplate;

    @InjectMocks
    SportsEventController sportsEventController;

    @BeforeEach
    void setUp() throws Exception {
        var topicField = SportsEventController.class.getDeclaredField("topic");
        topicField.setAccessible(true);
        topicField.set(sportsEventController, "test-topic");
    }

    @Test
    void testPublishEventOutcome() {
        SportsEventOutcome outcome = SportsEventOutcome.builder()
                .eventId(1L)
                .eventName("Event Name")
                .build();
        when(kafkaTemplate.send(anyString(), eq(outcome))).thenReturn(CompletableFuture.completedFuture(null));
        ResponseEntity<?> response = sportsEventController.publishEventOutcome(outcome);

        verify(kafkaTemplate, times(1)).send(anyString(), eq(outcome));
        assert response.getStatusCode().is2xxSuccessful();
        assert Objects.equals(response.getBody(), Map.of("message", "Event outcome published to Kafka"));
    }
}