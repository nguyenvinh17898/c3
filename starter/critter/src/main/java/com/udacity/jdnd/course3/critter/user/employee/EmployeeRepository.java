package com.udacity.jdnd.course3.critter.user.employee;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long>{
	
	@Query("SELECT e FROM EmployeeEntity e WHERE e.daysAvailable LIKE CONCAT('%', ?1, '%') AND e.skills LIKE CONCAT('%', ?2, '%')")
	List<EmployeeEntity> findByDaysAvailableAndSkills(String days, String skill);
}
