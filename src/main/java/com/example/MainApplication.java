package com.example;

import com.datastax.oss.driver.api.core.CqlSession;
import com.example.util.CassandraKeyspaceInitializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.InetSocketAddress;

@SpringBootApplication
public class MainApplication {

    public static void main(String[] args) {

        try (CqlSession cqlSession = CqlSession.builder().build()) {
            CassandraKeyspaceInitializer.createKeyspace(cqlSession);
        }
        SpringApplication.run(MainApplication.class, args);
    }



}
