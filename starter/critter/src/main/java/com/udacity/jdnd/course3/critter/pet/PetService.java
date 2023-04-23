package com.udacity.jdnd.course3.critter.pet;

import java.util.List;

public interface PetService {

	public PetEntity create(PetDTO petDTO);
	
	public PetEntity findById(Long id);
	
	public List<PetEntity> findByOwner(Long ownerId);
}
