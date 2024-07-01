package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import train.Train;

@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, Train.PassengerNameRecord> kafkaTemplate;

    @Autowired
    public KafkaProducerService(KafkaTemplate<String, Train.PassengerNameRecord> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(Train.PassengerNameRecord message) {
        kafkaTemplate.send("passenger_records", message); // Replace "your-topic-name" with your actual Kafka topic
    }


}
