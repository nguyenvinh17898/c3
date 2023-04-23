package com.udacity.jdnd.course3.critter.user;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udacity.jdnd.course3.critter.pet.PetEntity;
import com.udacity.jdnd.course3.critter.pet.PetService;
import com.udacity.jdnd.course3.critter.user.customer.CustomerDTO;
import com.udacity.jdnd.course3.critter.user.customer.CustomerEntity;
import com.udacity.jdnd.course3.critter.user.customer.CustomerService;
import com.udacity.jdnd.course3.critter.user.employee.EmployeeDTO;
import com.udacity.jdnd.course3.critter.user.employee.EmployeeEntity;
import com.udacity.jdnd.course3.critter.user.employee.EmployeeRequestDTO;
import com.udacity.jdnd.course3.critter.user.employee.EmployeeService;
import com.udacity.jdnd.course3.critter.user.employee.EmployeeSkill;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private PetService petService;
	
	@Autowired
	private CustomerService cusService;

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
    	customerDTO.setId(cusService.create(customerDTO).getId());
    	return customerDTO;
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers(){
    	List<CustomerEntity> customerEntities = cusService.findAll();
    	
    	for (CustomerEntity customerEntity : customerEntities) {
    		customerEntity.setPets(petService.findByOwner(customerEntity.getId()));
		}
    	
    	return cusService.formatData(customerEntities);
        
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId){
    	PetEntity petEntity = petService.findById(petId);
        
        CustomerEntity customerEntity = cusService.findById(petEntity.getCustomer().getId());
        
        List<PetEntity> petEntities = petService.findByOwner(customerEntity.getId());
        
        return new CustomerDTO(customerEntity.getId(), petEntities.stream().map(t -> t.getId()).collect(Collectors.toList()));
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
    	employeeDTO.setId(employeeService.create(employeeDTO).getId());
    	return employeeDTO;
    	
    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        EmployeeEntity employeeEntity = employeeService.findById(employeeId);
        
        return new EmployeeDTO(employeeEntity.getId(), employeeEntity.getName(), employeeEntity.getSkills(), employeeEntity.getDaysAvailable());
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        employeeService.updateDaysAvailable(employeeId, daysAvailable);
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
        List<EmployeeEntity> employeeEntities = employeeService.findEmployeesForService(employeeDTO.getDate(), employeeDTO.getSkills());
        List<EmployeeDTO> employeeDTOs = new ArrayList<>();
        
        for (EmployeeEntity e : employeeEntities) {
        	employeeDTOs.add(new EmployeeDTO(e.getId(), e.getName(), e.getSkills(), e.getDaysAvailable()));
		}
        
        return employeeDTOs;
    }

}
