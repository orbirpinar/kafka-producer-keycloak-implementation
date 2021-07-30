package com.orbirpinar.KafkaProducer;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class KafkaProducer {

    public static Producer<String,String> producer() {
        resetThreadContext();
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,IKafkaConstant.BOOSTRAP_SERVER);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
        return new org.apache.kafka.clients.producer.KafkaProducer<String, String>(props);
    }

    public static void publish(String value) {
        ProducerRecord<String, String> producerRecord = new ProducerRecord<>(IKafkaConstant.TOPIC_NAME,value);
        producer().send(producerRecord);
        // flush data
        producer().flush();
        // flush and close producer
        producer().close();
    }

    private static void resetThreadContext() {
        Thread.currentThread().setContextClassLoader(null);
    }
}
