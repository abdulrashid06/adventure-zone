package com.adventure.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.adventure.model.Activity;

public interface ActivityServiceInterface {

	
	public Activity addActivity(Activity activity);
	public Activity updateActivity(Integer activityId,Activity activity);
	public void DeleteActivity(Integer activityId);
	public List<Activity> viewAllactivity();
	public List<Activity> viewActivityofCharges(double activityCharges);
	public Integer countActivityofCharges(double activityCharges);
	
	
}
