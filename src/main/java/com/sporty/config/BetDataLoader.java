package com.sporty.config;

import com.sporty.dto.Bet;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.sporty.repository.BetRepository;

import java.math.BigDecimal;

@Configuration
public class BetDataLoader {

    @Bean
    CommandLineRunner loadBetData(BetRepository betRepository) {
        return args -> {
            betRepository.save(new Bet(1L, 1001L, 2001L, 3001L, 4001L, new BigDecimal("50.00")));
            betRepository.save(new Bet(2L, 1002L, 2002L, 3002L, 4002L,new BigDecimal("75.50")));
            betRepository.save(new Bet(3L, 1003L, 2003L, 3003L, 4003L,new BigDecimal("120.00")));

            System.out.println("Sample Bet data loaded:");
            betRepository.findAll().forEach(bet -> System.out.println(bet.toString()));
        };
    }
}
