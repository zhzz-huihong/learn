package org.example;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.kafka.streams.kstream.ValueMapper;
import org.apache.kafka.streams.state.KeyValueStore;

import java.util.Arrays;
import java.util.Locale;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;

/**
 * @author RenHao
 */
public class WorkCount {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(StreamsConfig.APPLICATION_ID_CONFIG, "streams-wordcount");
        properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "122.51.69.198:9092");
        //设置序列化库
        properties.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        properties.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());

        StreamsBuilder streamsBuilder = new StreamsBuilder();
        KStream<String, String> stream = streamsBuilder.stream("streams-plaintext-input");
        stream.flatMapValues(value -> Arrays.asList(value.toLowerCase(Locale.getDefault()).split("\\W+")))
                .groupBy((k,v)->v)
                .count(Materialized.as("counts-store"))
                .toStream()
                .to("streams-wordcount-output", Produced.with(Serdes.String(),Serdes.Long()));

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