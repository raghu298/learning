package org.learning.restapi.restapi.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.learning.restapi.restapi.dto.UserDTO;
import org.learning.restapi.restapi.entities.User;
import org.learning.restapi.restapi.exception.custom.AddressNotFoundException;
import org.learning.restapi.restapi.exception.custom.RecordNotFoundException;
import org.learning.restapi.restapi.exception.custom.UserNotFoundException;
import org.learning.restapi.restapi.repository.IUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * 
 * @author Biradar
 *
 */
@Service
public class UserService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private IUserRepository repository;
  
	
	public void createUser( UserDTO UserDTO) {
		LOGGER.info("Creating UserDTO for UserDTO name  {}", UserDTO.getName());
		repository.save(convertToUser(UserDTO));

	}

	
	public void updateUser( UserDTO userDTO,  int id) {
		LOGGER.info("update UserDTO for UserDTO name  {}", userDTO.getName());
		User existingUser = repository.findById(id).orElse(null);
		if(existingUser ==null) 
			throw new UserNotFoundException("UserDTO not found with the Id "+id);
		existingUser.setId(id);
		BeanUtils.copyProperties(userDTO, existingUser);
		repository.save(existingUser);

	}

	
	public UserDTO readUser( int id) {
		User user=  repository.findById(id).orElse(null);
		if(user ==null) 
			throw new UserNotFoundException("User not found with the Id "+id);
		return convertToUserDTO(user);
	}

	
	public List<UserDTO> readUsers() {
		List<User> user=  StreamSupport
				.stream(repository.findAll().spliterator(), false)
                .collect(Collectors.toList());
		
		return user.stream()
		          .map(this::convertToUserDTO)
		          .collect(Collectors.toList());
		
	}

	
	public void deleteUser( int id) {
		repository.deleteById(id);

	}
	
	public List<UserDTO> findByName(String name) {
		List<User> user=  repository.findByName(name);
		if(user.isEmpty())
			throw new RecordNotFoundException("user not found with the name "+name);
		return user.stream()
		          .map(this::convertToUserDTO)
		          .collect(Collectors.toList());
	}
	
	public List<UserDTO> findByAge(int age){
		List<User> user= repository.findByAge(age);
		if(user.isEmpty())
			throw new RecordNotFoundException("Records not found with the Age: "+age);
		return user.stream()
		          .map(this::convertToUserDTO)
		          .collect(Collectors.toList());
	}
	
	public List<UserDTO> findByAgeLessThan(int age){
		List<User> user=  repository.findByAgeLessThan(age);
		return user.stream()
		          .map(this::convertToUserDTO)
		          .collect(Collectors.toList());
	}
	
	public List<UserDTO> findByAddress(String address){
		List<User> user= repository.findByAddress(address);
		if(user.isEmpty())
			throw new AddressNotFoundException("Records not found with the address: "+address);
		return user.stream()
		          .map(this::convertToUserDTO)
		          .collect(Collectors.toList());
	}
	
	public List<UserDTO> findBetweenDob(int yr1,int yr2){
		List<User> user = repository.findBetweenDob(yr1,yr2);
		return user.stream()
		          .map(this::convertToUserDTO)
		          .collect(Collectors.toList());
	}
	
	public Page<UserDTO> getDetailsByAge(int age,Pageable page){
		Page<User> user= repository.getdetailsByAge(age, page);
		return  user.map(this::convertToUserDTO);
		
	}
	
	public List<UserDTO> getSortedDetailsByName(String name,Sort sort){
		List<User> user=  repository.findByNameAndSort(name, sort);
		return user.stream()
		          .map(this::convertToUserDTO)
		          .collect(Collectors.toList());
	}
	
	public User convertToUser(UserDTO userDTO) {
		return User.builder().id(userDTO.getId()).name(userDTO.getName()).age(userDTO.getAge())
		.dob(userDTO.getDob()).addess(userDTO.getAddess()).build();
	}
	
	public UserDTO convertToUserDTO(User user) {
		return UserDTO.builder().id(user.getId()).name(user.getName()).age(user.getAge())
		.dob(user.getDob()).addess(user.getAddess()).build();
	}
	
}
