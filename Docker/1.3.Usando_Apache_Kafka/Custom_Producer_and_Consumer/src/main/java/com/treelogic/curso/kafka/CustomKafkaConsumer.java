package com.treelogic.curso.kafka;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class CustomKafkaConsumer {
    
    private final static String TOPIC = "usandoapachekafka_2";
    private final static String BOOTSTRAP_SERVERS = "172.18.0.3:9092";
    
    private static Consumer<Long, String> createConsumer() {
        final Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,BOOTSTRAP_SERVERS);
        props.put(ConsumerConfig.GROUP_ID_CONFIG,"CustomKafkaConsumer");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, LongDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
  
        final Consumer<Long, String> consumer = new KafkaConsumer<>(props);
  
        consumer.subscribe(Collections.singletonList(TOPIC));
        return consumer;
    }

    static void runConsumer() throws InterruptedException {

        final Consumer<Long, String> consumer = createConsumer();
        
        while (true) {
            ConsumerRecords<Long, String> records = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<Long, String> record : records)
                System.out.printf("offset = %d, partition = %d, key = %s, value = %s%n",
                record.offset(), record.partition(), record.key(), record.value());
        }
    }

    public static void main(String... args) throws Exception {
        runConsumer();
    }
}