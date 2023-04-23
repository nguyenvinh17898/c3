package com.udacity.jdnd.course3.critter.user.employee;

import java.time.DayOfWeek;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Employee")
public class EmployeeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Column(name="name")
	private String name;

//	@ManyToMany(cascade = { CascadeType.ALL })
//	@JoinTable(name = "Employee_Skill", joinColumns = { @JoinColumn(name = "employee_id") }, inverseJoinColumns = {
//			@JoinColumn(name = "skill_id") })
	private String skills;
	
	private String daysAvailable;

	public EmployeeEntity() {
		super();
	}

	public EmployeeEntity(String name, String skills, String daysAvailable) {
		super();
		this.name = name;
		this.skills = skills;
		this.daysAvailable = daysAvailable;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<EmployeeSkill> getSkills() {
		String[] skillArray = this.skills.replace("[", "").replace("]", "").replace(" ", "").split(",");
        Set<EmployeeSkill> skills = new HashSet<>();
        for (String string : skillArray) {
        	skills.add(EmployeeSkill.valueOf(string));
		}
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	public Set<DayOfWeek> getDaysAvailable() {
		if(this.daysAvailable == null) return null;
		String[] dayArray = this.daysAvailable.replace("[", "").replace("]", "").replace(" ", "").split(",");
        Set<DayOfWeek> days = new HashSet<>();
        for (String string : dayArray) {
        	days.add(DayOfWeek.valueOf(string));
		}
		return days;
	}

	public void setDaysAvailable(String daysAvailable) {
		this.daysAvailable = daysAvailable;
	}

	
}
