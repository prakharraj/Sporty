package com.sporty.config;

import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Collections;
import java.util.Properties;

@Configuration
@Profile("!prod")
@Log4j2
public class KafkaTopicCreator {

    @Value("${kafka.topic.event-outcomes}")
    private String topicName;
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public CommandLineRunner createTopicAtStartup() {
        return args -> {
            Properties config = new Properties();
            config.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);

            try (AdminClient admin = AdminClient.create(config)) {
                NewTopic topic = new NewTopic(topicName, 1, (short) 1);
                // Create topic if not exists
                admin.createTopics(Collections.singleton(topic)).all().get();
                log.info("Kafka topic created: {}" , topicName);
            } catch (Exception e) {
                System.err.println("Failed to create Kafka topic: " + e.getMessage());
            }
        };
    }
}
