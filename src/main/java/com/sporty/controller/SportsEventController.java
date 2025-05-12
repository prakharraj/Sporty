package com.sporty.controller;

import com.sporty.dto.SportsEventOutcome;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class SportsEventController {

    @Value("${kafka.topic.event-outcomes}")
    private String topic;

    private final KafkaTemplate<String, SportsEventOutcome> kafkaTemplate;

    @PostMapping("/outcome")
    public ResponseEntity<?> publishEventOutcome(@Valid @RequestBody SportsEventOutcome outcome) {
        try {
            kafkaTemplate.send(topic, outcome)
                    .get(60, TimeUnit.SECONDS);
            return ResponseEntity.ok().body(Map.of("message", "Event outcome published to Kafka"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Failed to publish event outcome", "details", e.getMessage()));

        }
    }
}