//package org.example;
//
//
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Bean;
//import org.springframework.kafka.core.KafkaOperations;
//import org.springframework.kafka.listener.CommonErrorHandler;
//import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
//import org.springframework.kafka.listener.DefaultErrorHandler;
//import org.springframework.kafka.support.converter.JsonMessageConverter;
//import org.springframework.kafka.support.converter.RecordMessageConverter;
//import org.springframework.util.backoff.FixedBackOff;
//
///**
// * @author RenHao
// */
//@SpringBootApplication
//public class Main02 {
//    public static void main(String[] args) {
//        SpringApplication.run(Main02.class, args);
//    }
//
////    @Bean
////    //创建一个主题
////    public NewTopic topic() {
////        return new NewTopic("topic-learn", 1, (short) 1);
////    }
////
////    //创建监听器
////    @KafkaListener(id = "fooGroup", topics = "topic-learn")
////    public void listenTopic(String topic) {
////        System.out.println("Received: " + topic);
////    }
//
//    //记录消息转换器
//    @Bean
//    public RecordMessageConverter converter() {
//        return new JsonMessageConverter();
//    }
//
//    //错误异常处理
//    @Bean
//    public CommonErrorHandler errorHandler(KafkaOperations<Object, Object> template) {
//        return new DefaultErrorHandler(
//                new DeadLetterPublishingRecoverer(template), new FixedBackOff(1000L, 2));
//    }
//}