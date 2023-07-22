package com.adventure.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adventure.model.Activity;
import com.adventure.model.Ticket;
import com.adventure.service.ActivityServiceImplements;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/adventureZone")
public class ActivityController {

    @Autowired
    private ActivityServiceImplements actService;
   
    
    @PostMapping("/activities")
	public ResponseEntity<Activity> addActivity(@Valid @RequestBody Activity activity){
		
		return new ResponseEntity<Activity>(actService.addActivity(activity),HttpStatus.CREATED);
		
	}
	@PutMapping("/activities/{activityId}")
	public ResponseEntity<Activity> updateTicket(@PathVariable Integer activityId,@RequestBody Activity activity){
		
		return new ResponseEntity<Activity>(actService.updateActivity(activityId,activity),HttpStatus.ACCEPTED);
		
	}
	
	
	@DeleteMapping("/activities/{activityId}")
	public ResponseEntity<String> deleteTicket(@PathVariable Integer activityId){
		actService.DeleteActivity(activityId);
		return new ResponseEntity<String>("Activity Deleted Successfully.",HttpStatus.ACCEPTED);
	}
    
	@GetMapping("/activities")
	public ResponseEntity<List<Activity>> viewAllactivity(){
		return new ResponseEntity<List<Activity>>(actService.viewAllactivity(),HttpStatus.OK);
	}
    
    @GetMapping("/activities/{activityCharges}")
    public ResponseEntity<List<Activity>> viewActivityofCharges(@PathVariable double activityCharges){
    	return new ResponseEntity<List<Activity>>(actService.viewActivityofCharges(activityCharges),HttpStatus.OK);
    }
   
}
