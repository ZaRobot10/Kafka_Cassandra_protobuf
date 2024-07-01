package com.example.controller;

import com.example.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import train.Train;

@RestController
public class MessageController {

    private final KafkaProducerService kafkaProducerService;

    @Autowired
    public MessageController(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    @PostMapping("/send-message")
//    public ResponseEntity<String> sendMessage(@RequestBody Train.PassengerNameRecord passengerNameRecord) {
//        try {
//            kafkaProducerService.sendMessage(passengerNameRecord);
//            return ResponseEntity.ok("Message sent successfully");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send message: " + e.getMessage());
//        }
//    }
    public String sendMessage() {
        // Create your PassengerNameRecord instance as you defined
        Train.PassengerNameRecord passengerRecord = Train.PassengerNameRecord.newBuilder()
                .setPnr("ABC125")
                .addPassengers(
                        Train.Passenger.newBuilder()
                                .setName("Aki")
                                .setGender("Male")
                                .setAge(35)
                                .build()
                )
                .addJourneyLaps(
                        Train.JourneyLap.newBuilder()
                                .addPassengerIndexes(0)
                                .addJourneyDetails(
                                        Train.PassengerJourneyDetail.newBuilder()
                                                .setDateOfJourney("2024-06-28")
                                                .setTrainNumber("12346")
                                                .setTrainName("Express")
                                                .setBoardingStation("Anand Vihar Terminal")
                                                .setDestinationStation("Delhi Junction")
                                                .setCoachNumber(5)
                                                .setSeatNumber(10)
                                                .setTicketStatus("Confirmed")
                                                .setClassOfTravel("Sleeper")
                                                .build()
                                )
                                .addFareDetails(
                                        Train.PassengerFareDetail.newBuilder()
                                                .setFareType("Adult")
                                                .setAmount(600.0)
                                                .setCurrency("INR")
                                                .build()
                                )
                                .setAggregatedFareDetail(
                                        Train.AggregatedFareDetail.newBuilder()
                                                .setTotalAmount(600.0)
                                                .setCurrency("INR")
                                                .build()
                                )
                                .build()
                )
                .build();

        // Send the message using KafkaProducerService

        kafkaProducerService.sendMessage(passengerRecord);

        return "Message sent successfully";
    }
}
