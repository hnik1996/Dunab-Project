package com.dunab.example.demo.model.repository;

import com.dunab.example.demo.core.MongodbConfiguration;
import com.dunab.example.demo.model.entity.Station;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableMongoRepositories(basePackageClasses = MongodbConfiguration.class)
public interface StationRepository extends MongoRepository<Station, String> {
}
