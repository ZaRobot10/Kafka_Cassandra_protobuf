package com.example.repository;

import com.example.model.PassengerNameRecordEntity;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassengerNameRecordRepository extends CassandraRepository<PassengerNameRecordEntity, String> {
}
