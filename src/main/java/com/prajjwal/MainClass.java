package com.prajjwal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories("com.prajjwal.repositories.mongorepo")
@EnableElasticsearchRepositories("com.prajjwal.repositories.elasticrepo")

public class MainClass {

	public static void main(String[] args) {

		SpringApplication.run(MainClass.class, args);

	}

}
