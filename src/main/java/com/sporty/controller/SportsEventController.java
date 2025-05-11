package com.sporty.controller;

import com.sporty.dto.SportsEventOutcome;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class SportsEventController {

    private static final String TOPIC = "event-outcomes";

    private final KafkaTemplate<Long, SportsEventOutcome> kafkaTemplate;

    @PostMapping("/outcome")
    public String publishEventOutcome(@RequestBody SportsEventOutcome outcome) {
        kafkaTemplate.send(TOPIC, outcome);
        return "Event outcome published to Kafka";
    }
}