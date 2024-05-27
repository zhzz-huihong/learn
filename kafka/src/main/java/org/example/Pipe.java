package org.example;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.KStream;

import java.util.Properties;
import java.util.concurrent.CountDownLatch;

/**
 * @author RenHao
 */
public class Pipe {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(StreamsConfig.APPLICATION_ID_CONFIG, "streams-pipe");
        properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "122.51.69.198:9092");
//        设置序列化库
        properties.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        properties.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());

        StreamsBuilder streamsBuilder = new StreamsBuilder();
        KStream<String, String> stream = streamsBuilder.stream("streams-plaintext-input");
        stream.to("streams-pipe-output");

        Topology topology = streamsBuilder.build();
        System.out.println(topology.describe());

        KafkaStreams kafkaStreams = new KafkaStreams(topology, properties);
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Runtime.getRuntime().addShutdownHook(new Thread("streams-shutdown-hook"){
            @Override
            public void run() {
                kafkaStreams.close();
                countDownLatch.countDown();
            }
        });

        try {
            kafkaStreams.start();
            countDownLatch.await();
        } catch (InterruptedException e) {
            System.exit(1);
        }
        System.exit(0);
    }
}