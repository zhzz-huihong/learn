//package org.example.learn02;
//
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.util.StopWatch;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.concurrent.CompletableFuture;
//
///**
// * @author haoren
// * @date 2024/05/23 21:15
// **/
//@RestController
//public class Controller {
//    @Autowired
//    private KafkaTemplate<Object,Object> template;
//
//    @PostMapping(path = "/send/foo/{what}")
//    public void produce(@PathVariable String what) {
//        StopWatch watch = new StopWatch();
//        watch.start();
//        CompletableFuture[] futures = new CompletableFuture[10000];
//        for (int i = 0; i < 10000; i++) {
//            CompletableFuture send = template.send("topic-learn", "order.create."+i, "订单创建了："+i);
//            futures[i]=send;
//        }
//        CompletableFuture.allOf(futures).join();
//        watch.stop();
//        System.out.println("总耗时："+watch.getTotalTimeMillis());
//    }
//
//
//
//}
