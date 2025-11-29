package com.security.Configuration;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoConfig {

    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create(
                "mongodb+srv://youdontnomesonnn_db_user:sBoBQjuH1fferBQY@cluster0.wvsmtpa.mongodb.net/mydb?retryWrites=true&w=majority&appName=Cluster0"
        );
    }
}
