package com.udacity.jdnd.course3.critter.pet;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.udacity.jdnd.course3.critter.user.customer.CustomerEntity;

@Entity
@Table(name = "Pet")
public class PetEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;

	private String type;

	private LocalDate birthDate;

	private String notes;

	@ManyToOne
	@JoinColumn(name = "owner", nullable = false)
	private CustomerEntity customer;

	public PetEntity() {
		super();
	}

	public PetEntity(String name, String type, LocalDate birthDate, String notes, CustomerEntity customer) {
		super();
		this.name = name;
		this.type = type;
		this.birthDate = birthDate;
		this.notes = notes;
		this.customer = customer;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public CustomerEntity getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerEntity customer) {
		this.customer = customer;
	}

}
