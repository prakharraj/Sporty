package com.sporty.kafka;

import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.Properties;

@Configuration
@Log4j2
public class KafkaTopicCreator {
    public static void main(String[] args) {
        // Kafka broker address
        String bootstrapServers = "localhost:9092";

        // Admin client config
        Properties config = new Properties();
        config.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);

        // Create AdminClient
        try (AdminClient admin = AdminClient.create(config)) {
            // Define a new topic
            String topicName = "event-outcomes";
            int numPartitions = 1;
            short replicationFactor = 1;

            NewTopic newTopic = new NewTopic(topicName, numPartitions, replicationFactor);

            // Create topic
            admin.createTopics(Collections.singleton(newTopic)).all().get();
            log.info("Topic created: {}", topicName);
        } catch (Exception e) {
            log.error("Error creating topic: {}", e.getMessage());
        }
    }
}
