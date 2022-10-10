package com.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;


@SpringBootApplication
@EnableMongoAuditing

public class TaskPlannerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskPlannerApplication.class, args);
	}

}
