package org.learning.restapi.restapi.controller;

import java.util.List;

import org.learning.restapi.restapi.dto.UserDTO;
import org.learning.restapi.restapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class UserController implements IUserController {

	@Autowired
	private UserService service;
	
	@Override
	public void createUser(@RequestBody UserDTO UserDTO) {
		
		service.createUser(UserDTO);

	}

	@Override
	public void updateUser(@RequestBody UserDTO UserDTO, @PathVariable int id) {
		service.updateUser(UserDTO, id);

	}

	@Override
	public UserDTO readUser(@PathVariable int id) {
		return service.readUser(id);
	}

	@Override
	public List<UserDTO> readUsers() {
		return service.readUsers();
	}

	@Override
	public void deleteUser(@PathVariable int id) {
		service.deleteUser(id);

	}
	@Override
	public List<UserDTO> searchUSers(@RequestParam("name") String name) {
		return service.findByName(name);
	}

	@Override
	public List<UserDTO> searchUSersAge(int age) {

		return service.findByAge(age);
	}

	@Override
	public List<UserDTO> searchUSersLtAge(int age) {
		
		return service.findByAgeLessThan(age);
	}

	@Override
	public List<UserDTO> searchByAddress(String address) {
	
		return service.findByAddress(address);
	}

	@Override
	public List<UserDTO> searchBetDob(int year1, int year2) {
		
		return service.findBetweenDob(year1, year2);
	}

	@Override
	public Page<UserDTO> searchByAgeDetails(int age,Pageable page) {
		
		return service.getDetailsByAge(age,page);
	}

	@Override
	public List<UserDTO> searchDetailsBySortName(String name, String sortBy) {
		
		return service.getSortedDetailsByName(name, Sort.by("name"));
	}

}
