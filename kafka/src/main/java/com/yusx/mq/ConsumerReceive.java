package com.yusx.mq;

import com.yusx.utils.PropertiesReader;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;


import java.time.Duration;
import java.util.Arrays;

public class ConsumerReceive {

    public static void main(String[] args) {

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(PropertiesReader.readeProperties("consumer.properties"));
        consumer.subscribe(Arrays.asList("mytopic"));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> record : records)
                System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
        }
    }
}
