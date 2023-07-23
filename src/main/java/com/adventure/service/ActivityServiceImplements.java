package com.adventure.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adventure.exception.CustomerException;
import com.adventure.exception.NoRecordFoundException;
import com.adventure.model.Activity;
import com.adventure.model.Customer;
import com.adventure.repository.ActivityRespository;
import com.adventure.repository.CategoryRespository;


@Service
public class ActivityServiceImplements implements ActivityServiceInterface {

	@Autowired
	private ActivityRespository activityRepositry;
	
	@Override
	public Activity addActivity(Activity activity){
		if(activity==null) throw new CustomerException("The activity you have provided is null");
		Optional<Activity> act = activityRepositry.findById(activity.getActivityId());
		if(act.isPresent()) throw new CustomerException("Activity already exists");
		return activityRepositry.save(activity);
	}

	@Override
	public Activity updateActivity(Integer activityId, Activity activity) {
		Activity act = activityRepositry.findById(activityId).orElseThrow(() -> new NoRecordFoundException("No record found with the given id "+activityId));
		if(act.isDeleted()==true) throw new CustomerException("Activity is deleted");

		act.setActivityName(activity.getActivityName());
		act.setCharges(activity.getCharges());
		return activityRepositry.save(act);
	}

	@Override
	public void DeleteActivity(Integer activityId) {
		Activity activity = activityRepositry.findById(activityId).orElseThrow(() -> new NoRecordFoundException("No record found with the given id "+activityId));
		if(activity.isDeleted()==true) throw new CustomerException("Activity is already deleted");
		activity.setDeleted(true);
		
	}

	@Override
	public List<Activity> viewAllactivity() {
		List<Activity> activity = activityRepositry.findAll();
		if(activity.isEmpty()) throw new NoRecordFoundException("Activity list is empty");
		return activity;
	}

	@Override
	public List<Activity> viewActivityofCharges(double activityCharges) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer countActivityofCharges(double activityCharges) {
		// TODO Auto-generated method stub
		return null;
	}

}
