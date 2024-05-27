//package org.example.learn02;
//
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.kafka.annotation.PartitionOffset;
//import org.springframework.kafka.annotation.TopicPartition;
//import org.springframework.stereotype.Component;
//
///**
// * @author haoren
// * @date 2024/05/23 21:48
// **/
//@Component
//public class MessageListener {
//    @KafkaListener(topics = "topic-learn",groupId = "order-service")
//    public void listen(ConsumerRecord record){
//        System.out.println("收到消息："+record);
//        //可以监听到发给kafka的新消息，以前的拿不到
//    }
//
//    @KafkaListener(groupId = "order-service-2",topicPartitions = {
//            @TopicPartition(topic = "topic-learn",partitionOffsets = {
//                    @PartitionOffset(partition = "0",initialOffset = "0")
//            })
//    })
//    public void listenAll(ConsumerRecord record){
//        System.out.println("收到partion-0消息："+record);
//    }
//}
