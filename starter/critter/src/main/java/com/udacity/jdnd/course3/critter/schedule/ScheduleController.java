package com.udacity.jdnd.course3.critter.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {
	@Autowired
	private ScheduleService scheduleService;

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
    	scheduleService.create(scheduleDTO);
        return null;
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
    	List<ScheduleEntity> scheduleEntities = scheduleService.findAll();
    	List<Long> petList = scheduleEntities.stream().map(e->e.getPetId()).collect(Collectors.toList());
        List<Long> employeeList = scheduleEntities.stream().map(e->e.getEmployeeId()).collect(Collectors.toList());
        
        return scheduleEntities.stream().map(e->new ScheduleDTO(e.getId(), employeeList, petList, e.getDate(), e.getActivities())).collect(Collectors.toList());
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        throw new UnsupportedOperationException();
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        throw new UnsupportedOperationException();
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        throw new UnsupportedOperationException();
    }
}
