package com.example.config;

import io.confluent.kafka.serializers.protobuf.KafkaProtobufSerializer;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.serialization.BytesSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.kafka.support.serializer.StringOrBytesSerializer;
import train.Train;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    @Bean
    public KafkaTemplate<String, Train.PassengerNameRecord> kafkaTemplate() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put("bootstrap.servers", "localhost:19092");
        configProps.put("key.serializer", StringSerializer.class);
        configProps.put("value.serializer", KafkaProtobufSerializer.class);
        configProps.put("schema.registry.url", "http://localhost:8081");

        return new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(configProps));
    }
}
