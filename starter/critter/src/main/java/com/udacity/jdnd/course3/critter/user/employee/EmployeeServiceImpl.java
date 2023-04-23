package com.udacity.jdnd.course3.critter.user.employee;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public EmployeeEntity create(EmployeeDTO employeeDTO) {
		EmployeeEntity employeeEntity = new EmployeeEntity(employeeDTO.getName(), employeeDTO.getSkills().toString(), employeeDTO.getDaysAvailable()!=null ? employeeDTO.getDaysAvailable().toString():null);
		return employeeRepository.save(employeeEntity);
	}

	@Override
	public EmployeeEntity updateDaysAvailable(Long id, Set<DayOfWeek> daysAvailable) {
		Optional<EmployeeEntity> optional = employeeRepository.findById(id);
		EmployeeEntity employeeEntity = optional.get();
		
		employeeEntity.setDaysAvailable(daysAvailable.toString().replace("[", "").replace("]", "").replace(" ", ""));
		
		return employeeRepository.save(employeeEntity);
	}

	@Override
	public EmployeeEntity findById(Long id) {
		return employeeRepository.findById(id).get();
	}

	@Override
	public List<EmployeeEntity> findEmployeesForService(LocalDate localDate, Set<EmployeeSkill> employeeSkills) {
		List<EmployeeEntity> employeeEntities = new ArrayList<>();
        employeeEntities.addAll(employeeRepository.findByDaysAvailableAndSkills(localDate.getDayOfWeek().toString(), employeeSkills.toString().replace("[", "").replace("]", "")));

        return employeeEntities;

	}

}
