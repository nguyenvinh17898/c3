package com.udacity.jdnd.course3.critter.pet;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udacity.jdnd.course3.critter.user.customer.CustomerEntity;

@Service
public class PetrServiceImpl implements PetService {

	@Autowired
	private PetRepository petRepository;

	@Override
	public PetEntity create(PetDTO petDTO) {
		PetEntity petEntity = new PetEntity(petDTO.getName(), petDTO.getType().toString(), petDTO.getBirthDate(), petDTO.getNotes(), new CustomerEntity(petDTO.getOwnerId()));
		return petRepository.save(petEntity);
	}

	@Override
	public PetEntity findById(Long id) {
		return petRepository.findById(id).get();
	}

	@Override
	public List<PetEntity> findByOwner(Long ownerId) {
		return petRepository.findBycustomerId(ownerId);
	}
}
