package com.sporty.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

@Builder
public record SportsEventOutcome(
        @NotNull
        @Positive
        Long eventId,
        @NotNull
        @NotEmpty
        String eventName,
        @NotNull
        @Positive
        Long eventWinnerId) {
}