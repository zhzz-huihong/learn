package org.example.learn01;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.adapter.RecordFilterStrategy;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author haoren
 * @date 2024/05/26 21:08
 **/
@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    @Autowired
    private KafkaConfig kafkaConfig;

    @Bean
    public ConsumerFactory<String, Message> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                kafkaConfig.getBootstrapServers());
        props.put(
                ConsumerConfig.GROUP_ID_CONFIG,
                kafkaConfig.getConsumerConfig().getGroupId());
        props.put(
                ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,
                kafkaConfig.getConsumerConfig().getAutoOffsetReset());
        return new DefaultKafkaConsumerFactory<>(props,new StringDeserializer(),new JsonDeserializer<>(Message.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Message> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Message> factory
                = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setRecordFilterStrategy(consumerRecord -> consumerRecord.value().getMessage().contains("fuck"));
        return factory;
    }
}