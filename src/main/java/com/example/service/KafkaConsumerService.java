package com.example.service;

import com.example.model.PassengerNameRecordEntity;
import com.example.repository.PassengerNameRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import train.Train;

@Service
public class KafkaConsumerService {

    @Autowired
    private PassengerNameRecordRepository repository;

    @KafkaListener(topics = "passenger_records", groupId = "group_id")
    public void consume(Train.PassengerNameRecord passengerNameRecord) {
        PassengerNameRecordEntity entity = new PassengerNameRecordEntity();
        entity.setPnr(passengerNameRecord.getPnr());
        entity.setPassengerName(passengerNameRecord.getPassengers(0).getName());
        entity.setGender(passengerNameRecord.getPassengers(0).getGender());
        entity.setAge(passengerNameRecord.getPassengers(0).getAge());
        entity.setTrainNumber(passengerNameRecord.getJourneyLaps(0).getJourneyDetails(0).getTrainNumber());
        entity.setTrainName(passengerNameRecord.getJourneyLaps(0).getJourneyDetails(0).getTrainName());
        entity.setBoardingStation(passengerNameRecord.getJourneyLaps(0).getJourneyDetails(0).getBoardingStation());
        entity.setDestinationStation(passengerNameRecord.getJourneyLaps(0).getJourneyDetails(0).getDestinationStation());
        entity.setCoachNumber(String.valueOf(passengerNameRecord.getJourneyLaps(0).getJourneyDetails(0).getCoachNumber()));
        entity.setSeatNumber(passengerNameRecord.getJourneyLaps(0).getJourneyDetails(0).getSeatNumber());
        entity.setTicketStatus(passengerNameRecord.getJourneyLaps(0).getJourneyDetails(0).getTicketStatus());
        entity.setClassOfTravel(passengerNameRecord.getJourneyLaps(0).getJourneyDetails(0).getClassOfTravel());
        entity.setDateOfJourney(passengerNameRecord.getJourneyLaps(0).getJourneyDetails(0).getDateOfJourney());
        entity.setFareType(passengerNameRecord.getJourneyLaps(0).getFareDetails(0).getFareType());
        entity.setFareAmount(passengerNameRecord.getJourneyLaps(0).getFareDetails(0).getAmount());
        entity.setCurrency(passengerNameRecord.getJourneyLaps(0).getFareDetails(0).getCurrency());
        entity.setTotalAmount(passengerNameRecord.getJourneyLaps(0).getAggregatedFareDetail().getTotalAmount());

        repository.save(entity);
    }
}
