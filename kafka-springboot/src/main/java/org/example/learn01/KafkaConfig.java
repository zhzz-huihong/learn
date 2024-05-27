package org.example.learn01;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author haoren
 * @date 2024/05/26 22:56
 **/
@Component
@ConfigurationProperties(prefix = "spring.kafka")
public class KafkaConfig {
    @Getter
    @Setter
    private List<String> bootstrapServers;

    @Autowired
    @Getter
    @Setter
    private ConsumerConfig consumerConfig;

    @Data
    @Configuration
    @ConfigurationProperties("spring.kafka.consumer")
    public static class ConsumerConfig {
        private String groupId;
        private String autoOffsetReset;
    }
}
