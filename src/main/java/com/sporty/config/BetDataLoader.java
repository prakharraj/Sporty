package com.sporty.config;

import com.sporty.dto.Bet;
import com.sporty.repository.BetRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
@Log4j2
public class BetDataLoader {

    @Bean
    CommandLineRunner loadBetData(BetRepository betRepository) {
        return args -> {
            betRepository.save(new Bet(1L, 1001L, 2001L, 3001L, 4001L, new BigDecimal("50.00")));
            betRepository.save(new Bet(2L, 1002L, 2002L, 3002L, 4002L,new BigDecimal("75.50")));
            betRepository.save(new Bet(3L, 1003L, 2003L, 3003L, 4003L,new BigDecimal("120.00")));

            log.info("Sample Bet data loaded:  ");
            betRepository.findAll().forEach(
                    bet -> log.info(bet.toString()));
        };
    }
}
