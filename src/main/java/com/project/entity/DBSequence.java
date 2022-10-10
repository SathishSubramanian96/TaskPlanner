package com.project.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;

@Document(collection = "db_sequence")
@Data
@AllArgsConstructor
public class DBSequence {
 
	private static final String SEQUENCE_NAME = "user_sequence";
	
	@Id
	private String id;
	private int seq;
	
	
}
