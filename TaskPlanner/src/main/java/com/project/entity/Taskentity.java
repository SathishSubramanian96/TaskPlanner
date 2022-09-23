package com.project.entity;

import java.util.Date; 

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Document(collection = "task")
public class Taskentity {

	@Id
	 @JsonIgnore
	private String id;
	
	
	private String title;
	

	private String description;
	
	@CreatedBy
	@Field("createdBy")
    @JsonIgnore
	private String createdBy;
	
	
	private String assignedTo;
	
	@LastModifiedDate
	private Date completedOn;
	
	private String status;
	
	@Version      
     private Long version;
	
	
	
	
	
	
}
