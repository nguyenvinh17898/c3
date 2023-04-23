package com.udacity.jdnd.course3.critter.user.customer;

import java.util.List;

public interface CustomerService {

	public CustomerEntity create(CustomerDTO customerDTO);
	
	public List<CustomerEntity> findAll();
	
	public List<CustomerDTO> formatData(List<CustomerEntity> customerEntities);
	
	public CustomerEntity findById(Long id);
}
