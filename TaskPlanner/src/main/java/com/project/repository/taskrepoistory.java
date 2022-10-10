package com.project.repository;

import java.util.Optional;

import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.project.entity.Taskentity;
@JaversSpringDataAuditable
public interface taskrepoistory extends MongoRepository<Taskentity, Integer> {

	Optional<Taskentity> findById(int id);
	Optional<Taskentity> findByTitle(String title);
}
