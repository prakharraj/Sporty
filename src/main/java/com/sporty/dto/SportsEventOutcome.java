package com.sporty.dto;

import lombok.Builder;

@Builder
public record SportsEventOutcome(
        Long eventId,
        String eventName,
        Long eventWinnerId) {
}