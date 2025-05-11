package com.sporty.dto;

import lombok.Data;

@Data
public class SportsEventOutcome {
    private Long eventId;
    private String eventName;
    private Long eventWinnerId;
}