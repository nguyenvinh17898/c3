package com.udacity.jdnd.course3.critter.pet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

	@Autowired
	private PetService petService;

	@PostMapping
	public PetDTO savePet(@RequestBody PetDTO petDTO) {
		PetEntity petEntity = petService.create(petDTO);
		petDTO.setId(petEntity.getId());
		return petDTO;
	}

	@GetMapping("/{petId}")
	public PetDTO getPet(@PathVariable long petId) {
		PetEntity petEntity = petService.findById(petId);

		return new PetDTO(petEntity.getId(), PetType.valueOf(petEntity.getType()), petEntity.getName(),
				petEntity.getCustomer().getId(), petEntity.getBirthDate(), petEntity.getNotes());
	}

	@GetMapping
	public List<PetDTO> getPets() {
		throw new UnsupportedOperationException();
	}

	@GetMapping("/owner/{ownerId}")
	public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
		List<PetEntity> petEntities = petService.findByOwner(ownerId);
		List<PetDTO> petDTOs = new ArrayList<>();
		
		for (PetEntity petEntity : petEntities) {
			petDTOs.add(new PetDTO(petEntity.getId(), PetType.valueOf(petEntity.getType()), petEntity.getName(), petEntity.getCustomer().getId(), petEntity.getBirthDate(), petEntity.getNotes()));
		}
		
		return petDTOs;
	}
}
