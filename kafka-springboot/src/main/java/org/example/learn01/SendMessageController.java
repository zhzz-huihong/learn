package org.example.learn01;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * @author haoren
 * @date 2024/05/26 20:54
 **/
@Slf4j
@RestController
public class SendMessageController {

    @Autowired
    private KafkaTemplate<String, Message> kafkaTemplate;

    @GetMapping("send/{message}")
    public void send(@PathVariable String message) {
        CompletableFuture<SendResult<String, Message>> future = this.kafkaTemplate.send("test", new Message("mrbird",message));
        future.whenComplete((stringStringSendResult, throwable) -> log.info("成功发送消息：{}，offset=[{}]", message, stringStringSendResult.getRecordMetadata().offset()))
                .exceptionally(throwable -> {
                    log.error("消息：{} 发送失败，原因：{}", message, throwable.getMessage());
                    return null;
                });
    }
}