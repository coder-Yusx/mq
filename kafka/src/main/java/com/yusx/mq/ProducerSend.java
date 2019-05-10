package com.yusx.mq;

import com.yusx.utils.PropertiesReader;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class ProducerSend {

    public static void main(String[] args) {

        Producer<String,String> producer = new KafkaProducer<String, String>(PropertiesReader.readeProperties("producer.properties"));
        for (int i=0;i<=100;i++)
            producer.send(new ProducerRecord<String, String>("mytopic",Integer.toString(i),"msg"+i));
    }
}
