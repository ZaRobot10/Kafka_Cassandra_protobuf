package com.example.config;

import com.datastax.oss.driver.api.core.CqlSession;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;



@Configuration
public class CassandraConfig {

    @Autowired
    private CqlSession cqlSession;

    @PostConstruct
    private void createKeyspace() {

        String keyspaceName = "test10";
        String replicationStrategy = "SimpleStrategy";
        int replicationFactor = 1;

        // Create keyspace if it doesn't exist
        cqlSession.execute(String.format(
                "CREATE KEYSPACE IF NOT EXISTS %s WITH replication = {'class': '%s', 'replication_factor': %d}",
                keyspaceName, replicationStrategy, replicationFactor));


    }
}

// not working
