package com.newproject.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClients;

@Configuration
public class MongoConfig {

	@Bean
	public MongoTemplate mongoTemplate() {
		try {
			ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017/project_management");
			MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
					.applyConnectionString(connectionString).build();
			return new MongoTemplate(MongoClients.create(mongoClientSettings), "projectmanagement");
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

}
