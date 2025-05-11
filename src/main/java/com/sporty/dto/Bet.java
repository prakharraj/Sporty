package com.sporty.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.*;

import java.math.BigDecimal;

@Entity
@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
@Builder
public class Bet {

    @Id
    private Long betId;

    private Long userId;
    private Long eventId;
    private Long eventMarketId;
    private Long eventWinnerId;

    private BigDecimal betAmount;

}
