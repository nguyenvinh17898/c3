package com.udacity.jdnd.course3.critter.user.employee;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface EmployeeService {

	public EmployeeEntity create(EmployeeDTO employeeDTO);
	
	public EmployeeEntity updateDaysAvailable(Long id, Set<DayOfWeek> daysAvailable);
	
	public EmployeeEntity findById(Long id);
	
	public List<EmployeeEntity> findEmployeesForService(LocalDate localDate, Set<EmployeeSkill> employeeSkills);
	
}
