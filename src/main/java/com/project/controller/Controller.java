package com.project.controller;

import java.util.Optional;

import org.javers.core.Changes;
import org.javers.core.Javers;
import org.javers.repository.jql.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.entity.Taskentity;
import com.project.repository.taskrepoistory;
import com.project.service.SequenceGeneratorService;


@RestController
@RequestMapping("/task")

public class Controller {
	
	private final Javers javers;
    public Controller(Javers javers) {
        this.javers = javers;
    }
	
 @Autowired
 public taskrepoistory taskrepo;
 
 //Retrieve all the update history made to the Entity
 @GetMapping("/updates")
 public String getEmployeeChanges2() {
     QueryBuilder jqlQuery = QueryBuilder.byClass(Taskentity.class)
             .withNewObjectChanges();

     Changes changes = javers.findChanges(jqlQuery.build());

     return "<pre>" + changes.prettyPrint() + "</pre>";
 }
 
 //retrieve the objects by object id
 @GetMapping("/{id}")
 
	public Optional<Taskentity> getTaskEntitybyTaskId(@PathVariable("id") int id) {

		return taskrepo.findById(id);
 }
 
 //retrieve the objects by object title
 @GetMapping("/Title/{title}")
  

	public Optional<Taskentity> getTaskEntitybyTitle(@PathVariable("title") String title) {

		return taskrepo.findByTitle(title);
 }
 

    
 //updates the data by  object id
 @PutMapping("/{id}")
 
 public ResponseEntity<Taskentity> updateTaskEntitybyTaskId(@PathVariable("id") Integer id, @RequestBody Taskentity tutorial) {
   Optional<Taskentity> tutorialData = taskrepo.findById(id);

   if (tutorialData.isPresent()) {
     Taskentity _tutorial = tutorialData.get();
     _tutorial.setTitle(tutorial.getTitle());
     _tutorial.setCreatedBy(tutorial.getCreatedBy());
     _tutorial.setAssignedTo(tutorial.getAssignedTo());
     _tutorial.setDescription(tutorial.getDescription());
     _tutorial.setCompletedOn(tutorial.getCompletedOn());
     _tutorial.setStatus(tutorial.getStatus());
     return new ResponseEntity<>(taskrepo.save(_tutorial), HttpStatus.OK);
   } else {
     return new ResponseEntity<>(HttpStatus.NOT_FOUND);
   }
 }
 
 //assign to user and update status to "IN_PROGRESS"
 @PutMapping("/assign/{id}/{user}")
 public ResponseEntity<Taskentity> assignTaskEntitybyTaskId(@PathVariable("id") Integer id,@PathVariable("user") String user) {
   Optional<Taskentity> tutorialData = taskrepo.findById(id);
    String STATUS = "IN_PROGRESS";
   if (tutorialData.isPresent()) {
     Taskentity _tutorial = tutorialData.get();
     _tutorial.setStatus(STATUS);
     _tutorial.setAssignedTo(user);
     return new ResponseEntity<>(taskrepo.save(_tutorial), HttpStatus.OK);
   } else {
     return new ResponseEntity<>(HttpStatus.NOT_FOUND);
   }
 }
 
 @Autowired
 private SequenceGeneratorService seqService;
 
 // creates new data 
 @PostMapping(path ="/create" ,consumes="application/json")
 public Taskentity saveBook(@RequestBody Taskentity taskent){
	 
	 taskent.setId(seqService.getSequenceNumber(Taskentity.SEQUENCE_NAME));
	 return taskrepo.save(taskent);
     
     
 }

 
}