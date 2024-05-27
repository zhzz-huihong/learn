package org.example.learn01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * @author haoren
 * @date 2024/05/26 21:12
 **/
@Component
public class KafkaMessageListener {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @KafkaListener(topics = "test", groupId = "test-consumer")
//    @KafkaListener(groupId = "test-consumer"
//        ,topicPartitions = @TopicPartition(topic = "test",partitionOffsets = {@PartitionOffset(partition = "0",initialOffset = "0")})
//    )
//    @KafkaListener(groupId = "test-consumer"
//            ,topicPartitions = @TopicPartition(topic = "test",partitions = {"0","1"})
//    )
    public void listen(@Payload Message message,
                       @Header(KafkaHeaders.RECEIVED_PARTITION) String partition) {
        logger.info("接收消息: {},partition：{}", message,partition);
    }
}