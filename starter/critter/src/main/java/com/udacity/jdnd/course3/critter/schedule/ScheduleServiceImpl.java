package com.udacity.jdnd.course3.critter.schedule;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScheduleServiceImpl implements ScheduleService {

	@Autowired
	private ScheduleRepository scheduleRepository;

	@Override
	public void create(ScheduleDTO scheduleDTO) {
		for (int i = 0; i < scheduleDTO.getPetIds().size(); i++) {
			scheduleRepository
					.save(new ScheduleEntity(scheduleDTO.getPetIds().get(i), scheduleDTO.getEmployeeIds().get(i),
							scheduleDTO.getDate(), scheduleDTO.getActivities().toString()));
		}
	}

	@Override
	public List<ScheduleEntity> findAll() {
		return scheduleRepository.findAll();
	}

}
