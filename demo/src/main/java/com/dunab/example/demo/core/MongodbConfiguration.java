package com.dunab.example.demo.core;

import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.UnknownHostException;

@Configuration
public class MongodbConfiguration {

    @Bean
    public MongoClient mongoClient() throws UnknownHostException {
        return new MongoClient("http://localhost", 27017);
    }

    @Bean
    public MongoDBManager mongoDBManager(){
        return new MongoDBManager("dunab");
    }
}
