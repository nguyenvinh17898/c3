package com.udacity.jdnd.course3.critter.schedule;

import java.util.List;

public interface ScheduleService {

	public void create(ScheduleDTO scheduleDTO);
	
	public List<ScheduleEntity> findAll();
	
}
