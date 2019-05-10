package com.yusx.mq;

import com.yusx.utils.PropertiesReader;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;


import java.nio.Buffer;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConsumerReceive {

    public static void main(String[] args) {

        KafkaConsumer<String,String> kafkaConsumer = new KafkaConsumer<String, String>(PropertiesReader.readeProperties("producer.properties"));
        kafkaConsumer.subscribe(Arrays.asList("mytopic"));
        final int minBatchSize = 200;
        List<ConsumerRecord<String, String>> buffer = new ArrayList<>();
        while (true){
            ConsumerRecords<String, String> consumerRecords = kafkaConsumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> receive:consumerRecords){
                buffer.add(receive);
            }
            if(buffer.size() >= minBatchSize){
                kafkaConsumer.commitSync();
                buffer.clear();
            }
        }
    }
}
