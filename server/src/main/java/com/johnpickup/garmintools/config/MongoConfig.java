package com.johnpickup.garmintools.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
@Slf4j
public class MongoConfig {
    @Value("${mongodb.uri}")
    private String mongoDbUri;

    @Bean
    MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoDbFactory());
    }

    @Bean
    MongoDatabaseFactory mongoDbFactory() {
        log.info("Connecting to MongoDB at {}", mongoDbUri);
        return MongoDatabaseFactory.create(mongoDbUri);
    }
}
