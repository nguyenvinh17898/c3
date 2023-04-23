package com.udacity.jdnd.course3.critter.user.customer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udacity.jdnd.course3.critter.pet.PetEntity;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public CustomerEntity create(CustomerDTO customerDTO) {
		CustomerEntity customerEntity = new CustomerEntity(customerDTO.getName(), customerDTO.getPhoneNumber());
		return customerRepository.save(customerEntity);
	}

	@Override
	public List<CustomerEntity> findAll() {
		// TODO Auto-generated method stub
		return customerRepository.findAll();
	}
	
	@Override
	public List<CustomerDTO> formatData(List<CustomerEntity> customerEntities){
		List<CustomerDTO> customerDTOs = new ArrayList<>();
		CustomerDTO customerDTO;
		for (CustomerEntity customerEntity : customerEntities) {
			customerDTO = new CustomerDTO();
			customerDTO.setId(customerEntity.getId());
			customerDTO.setName(customerEntity.getName());
			customerDTO.setPetIds(customerEntity.getPets().stream().map(PetEntity::getId).collect(Collectors.toList()));
			customerDTOs.add(customerDTO);
		}
		
		return customerDTOs;
		
	}

	@Override
	public CustomerEntity findById(Long id) {
		return customerRepository.findById(id).get();
	}
}
