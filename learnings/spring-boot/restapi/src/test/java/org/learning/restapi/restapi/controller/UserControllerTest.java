package org.learning.restapi.restapi.controller;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.learning.restapi.restapi.dto.UserDTO;
import org.learning.restapi.restapi.service.UserService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * 
 * @author Biradar
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {
	
	@InjectMocks
	private UserController userController;
	
	@Mock
	private UserService service;
	
	@Test
	public void testCreate() {
		UserDTO userDTO = Mockito.mock(UserDTO.class);
		//Mockito.when(userDTO.getName()).thenReturn("Test");
	//	Mockito.doNothing().when(service).createUser(userDTO);
		userController.createUser(userDTO);
		
	}
	
	@Test
	public void testGetUserById() {
		UserDTO userDTO = Mockito.mock(UserDTO.class);
		//Mockito.when(userDTO.getName()).thenReturn("Test");
	    Mockito.when(service.readUser(10)).thenReturn(userDTO);
		 userDTO =userController.readUser(10);
		 assertNotNull("User retreiwd for id ", userDTO);
		
	}

}
