package com.udacity.jdnd.course3.critter.schedule;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.udacity.jdnd.course3.critter.user.employee.EmployeeSkill;

@Entity
@Table(name = "schedule")
public class ScheduleEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private Long petId;

	private Long employeeId;

	private LocalDate date;

	private String activities;

	public ScheduleEntity(Long petId, Long employeeId, LocalDate date, String activities) {
		super();
		this.petId = petId;
		this.employeeId = employeeId;
		this.date = date;
		this.activities = activities;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPetId() {
		return petId;
	}

	public void setPetId(Long petId) {
		this.petId = petId;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Set<EmployeeSkill>  getActivities() {
		String[] skillArray = this.activities.replace("[", "").replace("]", "").replace(" ", "").split(",");
        Set<EmployeeSkill> activities = new HashSet<>();
        for (String ac : skillArray) {
        	activities.add(EmployeeSkill.valueOf(ac));
		}
		return activities;
	}

	public void setActivities(String activities) {
		this.activities = activities;
	}

}
